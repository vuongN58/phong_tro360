package ptit.vuong.phongtro.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import ptit.vuong.phongtro.extension.launchAndRepeatStarted
import ptit.vuong.phongtro.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import ptit.vuong.phongtro.R
import ptit.vuong.phongtro.databinding.FragmentHomeBinding
import ptit.vuong.phongtro.extension.onClick
import ptit.vuong.phongtro.utils.AppConstant.KEY_ROOM_ID

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val viewModel: HomeViewModel by viewModels()

    private val roomAdapter by lazy(LazyThreadSafetyMode.NONE) {
        AdvertPagingAdapter { item ->
            navigateTo(
                R.id.action_homeFragment_to_roomFragment,
                bundleOf(
                    KEY_ROOM_ID to item.id
                )
            )
        }
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate

    override fun initData(data: Bundle?) {
    }

    override fun initViews() {
        binding.rvRoom.adapter = roomAdapter
    }

    override fun initActions() {
        binding.fabRentRoom.onClick {
            navigateTo(R.id.action_homeFragment_to_rentRoomFragment)
        }
    }

    override fun initObservers() {
        launchAndRepeatStarted({
            viewModel.uiState.collect(::renderUi)
        }, {
            roomAdapter.addLoadStateListener { loadState ->
                when (loadState.refresh) {
                    is LoadState.Loading -> {
                        showLoading()
                    }

                    is LoadState.NotLoading -> {
                        hideLoading()
                    }

                    is LoadState.Error -> {
                        hideLoading()
                    }
                }
            }
        }
        )
    }

    private fun renderUi(uiState: HomeUiState) {
        launchAndRepeatStarted({
            roomAdapter.submitData(uiState.data)
        })

    }

}