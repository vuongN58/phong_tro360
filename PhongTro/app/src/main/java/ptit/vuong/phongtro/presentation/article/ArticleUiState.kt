package ptit.vuong.phongtro.presentation.article

import ptit.vuong.phongtro.domain.model.ArticleModel

data class ArticleUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val articles: List<ArticleModel> = emptyList()
)