package ptit.vuong.phongtro.presentation.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.os.bundleOf
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import ptit.vuong.phongtro.extension.launchAndRepeatStarted
import ptit.vuong.phongtro.extension.onClick
import ptit.vuong.phongtro.extension.visibleIf
import ptit.vuong.phongtro.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import ptit.vuong.phongtro.R
import ptit.vuong.phongtro.databinding.FragmentSearchBinding
import ptit.vuong.phongtro.extension.gone
import ptit.vuong.phongtro.extension.visible
import ptit.vuong.phongtro.presentation.home.AdvertPagingAdapter
import ptit.vuong.phongtro.presentation.search.dialog.FilterBottomSheet
import ptit.vuong.phongtro.utils.AppConstant.KEY_ROOM_ID

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>() {

    private val viewModel: SearchViewModel by viewModels()

    private val advertAdapter: AdvertPagingAdapter by lazy(LazyThreadSafetyMode.NONE) {
        AdvertPagingAdapter { item ->
            navigateTo(
                R.id.action_searchFragment_to_roomFragment,
                bundleOf(
                    KEY_ROOM_ID to item.id
                )
            )
        }
    }

    private val searchHistoryAdapter: SearchHistoryAdapter by lazy(LazyThreadSafetyMode.NONE) {
        SearchHistoryAdapter {
            viewModel.searchAdverts(query = it.content)
            binding.etSearch.setText(it.content)
        }
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSearchBinding
        get() = FragmentSearchBinding::inflate

    override fun initData(data: Bundle?) {
    }

    override fun initViews() {
        binding.rvSearch.adapter = advertAdapter
        binding.rvSearchHistory.adapter = searchHistoryAdapter
    }

    override fun initActions() {
        binding.run {
            etSearch.setOnEditorActionListener { v, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    viewModel.searchAdverts(query = v.text.toString())
                    true
                } else {
                    false
                }
            }
            etSearch.doAfterTextChanged { text ->
                if (text.isNullOrEmpty()) {
                    historyLL.visible()
                    rvSearch.gone()
                } else {
                    historyLL.gone()
                    rvSearch.visible()
                }
            }
            fabFilter.onClick {
                FilterBottomSheet.showBottomSheet(this@SearchFragment).apply {
                    onApplyClick = { filter ->
                        viewModel.searchAdverts(
                            query = etSearch.text.toString(),
                            minSize = filter.minSize,
                            maxSize = filter.maxSize,
                            minPrice = filter.minPrice,
                            maxPrice = filter.maxPrice,
                        )
                    }
                }
            }
        }
    }

    override fun initObservers() {
        launchAndRepeatStarted({
            viewModel.uiState.collect(::renderUi)
        },
            {
                advertAdapter.addLoadStateListener { loadState ->
                    binding.progressBar.visibleIf { loadState.refresh is LoadState.Loading }
                    binding.rvSearch.visibleIf { loadState.refresh is LoadState.NotLoading }
                    binding.tvState.visibleIf { loadState.refresh is LoadState.Error }
                    binding.fabFilter.visibleIf { loadState.refresh is LoadState.NotLoading }
                }
            })
    }

    private fun renderUi(searchUiState: SearchUiState) {
        searchHistoryAdapter.submitList(searchUiState.searchHistoryList)
        launchAndRepeatStarted({
            advertAdapter.submitData(searchUiState.searchData)
            binding.rvSearchHistory.visibleIf { advertAdapter.itemCount == 0 }
        })
    }

}