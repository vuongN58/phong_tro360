package ptit.vuong.phongtro.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ptit.vuong.phongtro.data.local.AppRoomDatabase
import ptit.vuong.phongtro.domain.mapper.toEntity
import ptit.vuong.phongtro.domain.mapper.toModel
import ptit.vuong.phongtro.domain.model.SearchHistoryModel
import ptit.vuong.phongtro.domain.repository.SearchHistoryRepository
import ptit.vuong.phongtro.extension.asResultFlow
import javax.inject.Inject

class SearchHistoryRepositoryImpl @Inject constructor(
    private val database: AppRoomDatabase,
) : SearchHistoryRepository {

    override fun getHistory(): Flow<Result<List<SearchHistoryModel>>> =
        database
            .searchDao()
            .getHistory()
            .map { entity ->
                entity.map {
                    it.toModel()
                }
            }
            .asResultFlow()

    override suspend fun insertHistory(searchHistoryModel: SearchHistoryModel) =
        database.searchDao().insertHistory(searchHistoryModel.toEntity())
}