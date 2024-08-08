package ptit.vuong.phongtro.presentation.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import ptit.vuong.phongtro.R
import ptit.vuong.phongtro.databinding.FragmentFavoriteBinding
import ptit.vuong.phongtro.extension.launchAndRepeatStarted
import ptit.vuong.phongtro.extension.onClick
import ptit.vuong.phongtro.extension.visibleIf
import ptit.vuong.phongtro.presentation.base.BaseFragment
import ptit.vuong.phongtro.presentation.home.AdvertPagingAdapter
import ptit.vuong.phongtro.presentation.home.HomeViewModel
import ptit.vuong.phongtro.utils.AppConstant.KEY_ROOM_ID

@AndroidEntryPoint
class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>() {

    private val viewModel: FavoriteViewModel by viewModels()

    private val advertAdapter by lazy(LazyThreadSafetyMode.NONE) {
        FavoriteAdapter { item ->
            navigateTo(
                R.id.action_favoriteFragment_to_roomFragment,
                bundleOf(
                    KEY_ROOM_ID to item.id
                )
            )
        }
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentFavoriteBinding
        get() = FragmentFavoriteBinding::inflate

    override fun initData(data: Bundle?) {
    }

    override fun initViews() {
        binding.rvFavorite.adapter = advertAdapter
    }

    override fun initActions() {
        binding.ivBack.onClick {
            popBackStack()
        }
    }

    override fun initObservers() {
        launchAndRepeatStarted({
            viewModel.uiState.collect(::renderUi)
        })
    }

    private fun renderUi(state: FavoriteUiState) {
        advertAdapter.submitList(state.data)
        binding.run {
            tvEmpty.visibleIf { state.data.isEmpty() }
            rvFavorite.visibleIf { state.data.isNotEmpty() }
        }

    }
}