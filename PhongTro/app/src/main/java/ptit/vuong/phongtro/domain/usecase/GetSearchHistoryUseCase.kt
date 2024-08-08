package ptit.vuong.phongtro.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flowOn
import ptit.vuong.phongtro.data.di.IoDispatcher
import ptit.vuong.phongtro.domain.repository.SearchHistoryRepository
import javax.inject.Inject

/**
 * Get search history use case
 * @param searchHistoryRepository
 * @param dispatcher
 * @constructor Create empty Get search history use case
 */

class GetSearchHistoryUseCase @Inject constructor(
    private val searchHistoryRepository: SearchHistoryRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) {
    operator fun invoke() = searchHistoryRepository.getHistory().flowOn(dispatcher)
}