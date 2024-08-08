package ptit.vuong.phongtro.data.repository

import ptit.vuong.phongtro.data.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ptit.vuong.phongtro.data.di.RentRoomServiceWithoutAuthQualifier
import ptit.vuong.phongtro.data.di.RentRoomWithoutAuthQualifier
import ptit.vuong.phongtro.data.remote.RentRoomService
import ptit.vuong.phongtro.domain.mapper.toArticleModel
import ptit.vuong.phongtro.domain.model.ArticleModel
import ptit.vuong.phongtro.domain.repository.ArticleRepository
import ptit.vuong.phongtro.utils.runSuspendCatching
import javax.inject.Inject

class ArticleRepositoryImpl @Inject constructor(
    @RentRoomServiceWithoutAuthQualifier private val articleService: RentRoomService,
    @IoDispatcher private val coroutineDispatchers: CoroutineDispatcher,
) : ArticleRepository {
    override suspend fun getArticles(): Result<List<ArticleModel>> = runSuspendCatching {
        withContext(coroutineDispatchers) {
            articleService.getArticles().result?.map { it.toArticleModel() } ?: emptyList()
        }
    }
}