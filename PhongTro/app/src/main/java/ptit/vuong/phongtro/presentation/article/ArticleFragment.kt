package ptit.vuong.phongtro.presentation.article

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import ptit.vuong.phongtro.extension.launchAndRepeatStarted
import ptit.vuong.phongtro.extension.navigateToUrl
import ptit.vuong.phongtro.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import ptit.vuong.phongtro.databinding.FragmentArticleBinding

@AndroidEntryPoint
class ArticleFragment : BaseFragment<FragmentArticleBinding>() {
    private val articleAdapter by lazy(LazyThreadSafetyMode.NONE) {
        ArticleAdapter { item ->
            requireContext().navigateToUrl(item.url)
        }
    }

    private val viewModel: ArticleViewModel by viewModels()

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentArticleBinding
        get() = FragmentArticleBinding::inflate

    override fun initData(data: Bundle?) {
    }

    override fun initViews() {
        binding.rvArticle.adapter = articleAdapter
    }

    override fun initActions() {
    }

    override fun initObservers() {
        launchAndRepeatStarted({
            viewModel.uiState.collect(::renderUi)
        })
    }

    private fun renderUi(uiState: ArticleUiState) {
        articleAdapter.submitList(uiState.articles)
    }

}