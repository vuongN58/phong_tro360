package ptit.vuong.phongtro.domain.repository

import ptit.vuong.phongtro.domain.model.ArticleModel

interface ArticleRepository {
    suspend fun getArticles(): Result<List<ArticleModel>>
}