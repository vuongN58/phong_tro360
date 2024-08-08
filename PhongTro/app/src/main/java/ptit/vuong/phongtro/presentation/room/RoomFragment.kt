package ptit.vuong.phongtro.presentation.room

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import ptit.vuong.phongtro.extension.callToOwner
import ptit.vuong.phongtro.extension.launchAndRepeatStarted
import ptit.vuong.phongtro.extension.onClick
import ptit.vuong.phongtro.extension.openMap
import ptit.vuong.phongtro.extension.sendSms
import ptit.vuong.phongtro.extension.shareRoom
import ptit.vuong.phongtro.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import ptit.vuong.phongtro.R
import ptit.vuong.phongtro.databinding.FragmentRoomBinding
import ptit.vuong.phongtro.extension.addSquareMeter
import ptit.vuong.phongtro.extension.convertToVietnamDong
import ptit.vuong.phongtro.extension.toHtml
import ptit.vuong.phongtro.extension.toPassedTime
import ptit.vuong.phongtro.extension.visible
import ptit.vuong.phongtro.presentation.room.dialog.OptionBottomSheet
import ptit.vuong.phongtro.presentation.room.widget.RelatedItem
import ptit.vuong.phongtro.utils.AppConstant

@AndroidEntryPoint
class RoomFragment : BaseFragment<FragmentRoomBinding>(), OnMapReadyCallback {
    private val viewModel: RoomViewModel by viewModels()
    private val photoAdapter by lazy(LazyThreadSafetyMode.NONE) {
        PhotoAdapter()
    }
    private lateinit var mapView: MapView
    private lateinit var googleMap: GoogleMap

    private val pageChangeCallBack by lazy(LazyThreadSafetyMode.NONE) {
        object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                val currentPosition = position.plus(1).toString() + "/" + photoAdapter.itemCount
                binding.tvPosition.text = currentPosition
            }
        }
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRoomBinding
        get() = FragmentRoomBinding::inflate

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        mapView = binding.mapView
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)
        return view
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
    }

    override fun initData(data: Bundle?) {}

    override fun initViews() {
        binding.vpRoomImages.adapter = photoAdapter
    }

    override fun initActions() {
        binding.vpRoomImages.registerOnPageChangeCallback(pageChangeCallBack)
        binding.ivBack.onClick {
            popBackStack()
        }
        binding.fabBookRoom.onClick {
            OptionBottomSheet.showBottomSheet(
                fragment = this,
                isFavorite = viewModel.uiState.value.isFavorite
            ).apply {
                onPhoneCallClick = {
                    requireContext().callToOwner(this@RoomFragment.binding.tvPhone.text.toString())
                }
                onMessageClick = {
                    requireContext().sendSms(this@RoomFragment.binding.tvPhone.text.toString())
                }
                onPositionClick = {
                    val lang = viewModel.uiState.value.data?.room?.lng
                    val lat = viewModel.uiState.value.data?.room?.lat
                    requireContext().openMap(
                        lat = lat?.toDoubleOrNull() ?: 0.0,
                        lng = lang?.toDoubleOrNull() ?: 0.0,
                    )
                }
                onShareClick = {
                    //LGTM !!!!
                    requireContext().shareRoom(
                        room = viewModel.uiState.value.data?.room.toString(),
                    )
                }
                onFavoriteClick = viewModel::setFavourite
            }
        }
    }

    override fun initObservers() {
        launchAndRepeatStarted({
            viewModel.uiState.collect(::renderUi)
        })
    }

    private fun renderUi(uiState: RoomUiState) {
        binding.run {
            if (uiState.isLoading) {
                showLoading()
            } else {
                nsvRoomDetail.visible()
                hideLoading()
            }
            tvPrice.text = uiState.data?.room?.price?.convertToVietnamDong()
            tvAddress.text = uiState.data?.room?.address
            tvDescription.text = uiState.data?.room?.description?.toHtml()
            tvSize.text = uiState.data?.room?.size.toString().addSquareMeter()
            tvRoomName.text = uiState.data?.room?.title
            tvPhone.text = uiState.data?.room?.contactPhone
            val position =
                vpRoomImages.currentItem.plus(1)
                    .toString() + "/" + uiState.data?.room?.photoModels?.size
            tvPosition.text = position
            photoAdapter.submitList(uiState.data?.room?.photoModels)
            mapView.getMapAsync { map ->
                val coordinate = LatLng(
                    uiState.data?.room?.lat?.toDouble() ?: 0.0,
                    uiState.data?.room?.lng?.toDouble() ?: 0.0
                )
                map.addMarker(MarkerOptions().position(coordinate))
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(coordinate, 15f))
            }
            tvTime.text = uiState.data?.room?.createdAt?.toPassedTime()
            uiState.data?.relatedModels?.forEach { room ->
                llRelated.addView(RelatedItem(requireContext()).apply {
                    init(room)
                    setClickListener { item ->
                        navigateTo(
                            R.id.action_roomFragment_self,
                            bundleOf(AppConstant.KEY_ROOM_ID to item.id)
                        )
                    }
                })
            }
        }
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onDestroyView() {
        binding.vpRoomImages.unregisterOnPageChangeCallback(pageChangeCallBack)
        mapView.onDestroy()
        super.onDestroyView()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }
}