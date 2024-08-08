package ptit.vuong.phongtro.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ptit.vuong.phongtro.data.di.IoDispatcher
import ptit.vuong.phongtro.domain.model.SearchHistoryModel
import ptit.vuong.phongtro.domain.repository.SearchHistoryRepository
import javax.inject.Inject

/**
 * Insert search history use case
 * @param searchHistoryRepository
 * @param dispatcher
 * @constructor Create empty Insert search history use case
 */

class InsertSearchHistoryUseCase @Inject constructor(
    private val searchHistoryRepository: SearchHistoryRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) {

    suspend operator fun invoke(searchHistoryModel: SearchHistoryModel) =
        withContext(dispatcher) {
            if (searchHistoryModel.content.isNotEmpty()) {
                searchHistoryRepository.insertHistory(searchHistoryModel)
            }
        }

}