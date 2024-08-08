package ptit.vuong.phongtro.domain.usecase

import ptit.vuong.phongtro.domain.model.ArticleModel
import ptit.vuong.phongtro.domain.repository.ArticleRepository
import javax.inject.Inject

/**
 * Get articles use case
 * @param articleRepository
 * @constructor Create empty Get articles use case
 */

class GetArticlesUseCase @Inject constructor(
    private val articleRepository: ArticleRepository
) {
    suspend operator fun invoke(): Result<List<ArticleModel>> = articleRepository.getArticles()
}