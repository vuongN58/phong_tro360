package ptit.vuong.phongtro.domain.repository

import kotlinx.coroutines.flow.Flow
import ptit.vuong.phongtro.domain.model.SearchHistoryModel

interface SearchHistoryRepository {

    fun getHistory() : Flow<Result<List<SearchHistoryModel>>>

    suspend fun insertHistory(searchHistoryModel: SearchHistoryModel)

}