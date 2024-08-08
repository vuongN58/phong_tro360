package ptit.vuong.phongtro.presentation


import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat.checkSelfPermission
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.LocationSettingsResponse
import com.google.android.gms.location.SettingsClient
import com.google.android.gms.tasks.Task
import dagger.hilt.android.AndroidEntryPoint
import ptit.vuong.phongtro.R
import ptit.vuong.phongtro.databinding.FragmentRentRoomBinding
import ptit.vuong.phongtro.extension.onClick
import ptit.vuong.phongtro.extension.toaster
import ptit.vuong.phongtro.extension.turnOnGps
import ptit.vuong.phongtro.presentation.base.BaseFragment
import java.util.Locale


@AndroidEntryPoint
class RentRoomFragment : BaseFragment<FragmentRentRoomBinding>() {
    private val fusedLocationClient: FusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(requireContext())
    }

    private val locationPermissionResultLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { isGranted ->
            val fineLocation = isGranted[android.Manifest.permission.ACCESS_FINE_LOCATION] ?: false
            val coarseLocation =
                isGranted[android.Manifest.permission.ACCESS_COARSE_LOCATION] ?: false
            if (fineLocation && coarseLocation) {
                getCurrentLocation()
            } else {
                requireContext().toaster("Please turn on GPS")
                requireContext().turnOnGps()
            }
        }


    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRentRoomBinding
        get() = FragmentRentRoomBinding::inflate

    override fun initData(data: Bundle?) {}

    override fun initViews() {
        initSteps()
    }

    override fun initActions() {
        binding.run {
            ivBack.onClick {
                popBackStack()
            }
            tvCurrentPosition.onClick {
                requestLocationPermission()
            }
        }
    }

    override fun initObservers() {}

    private fun requestLocationPermission() {
        locationPermissionResultLauncher.launch(
            arrayOf(
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }

    private fun getCurrentLocation() {
        if (hasLocationPermissions()) {
            showLoading()
            checkLocationSettings { isLocationEnabled ->
                if (isLocationEnabled) {
                    fetchLastKnownLocation()
                } else {
                    hideLoading()
                    requireContext().toaster("Please turn on GPS")
                    requireContext().turnOnGps()
                }
            }
        } else {
            requestLocationPermission()
        }
    }

    private fun initSteps() {
        binding.stepView.state
            .steps(
                resources.getStringArray(R.array.rent_room_steps).toMutableList()
            )
            .stepsNumber(4)
            .commit()
    }

    private fun hasLocationPermissions(): Boolean {
        return checkSelfPermission(
            requireContext(),
            android.Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED &&
                checkSelfPermission(
                    requireContext(),
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
    }

    private fun checkLocationSettings(onResult: (Boolean) -> Unit) {
        val builder = LocationSettingsRequest.Builder()
        val client: SettingsClient = LocationServices.getSettingsClient(requireContext())
        val task: Task<LocationSettingsResponse> = client.checkLocationSettings(builder.build())

        task.addOnSuccessListener {
            onResult(true)
        }

        task.addOnFailureListener {
            onResult(false)
        }
    }

    @SuppressLint("MissingPermission")
    private fun fetchLastKnownLocation() {
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                hideLoading()
                location?.let {
                    fetchAddressFromLocation(it)
                } ?: run {
                    requireContext().toaster("Unable to get current location")
                }
            }
    }

    private fun fetchAddressFromLocation(location: Location) {
        val geocoder = Geocoder(requireContext(), Locale("vi"))
        val addresses = geocoder.getFromLocation(location.latitude, location.longitude, 1)
        if (addresses?.isNotEmpty() == true) {
            binding.apply {
                etProvince.setText(addresses[0].adminArea)
                etDistrict.setText(addresses[0].subAdminArea)
                etWard.setText(addresses[0].subLocality)
                etStreet.setText(addresses[0].thoroughfare)
            }
        } else {
            requireContext().toaster("Unable to get current location")
        }
    }
}