package ptit.vuong.phongtro.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flowOn
import ptit.vuong.phongtro.data.di.IoDispatcher
import ptit.vuong.phongtro.domain.repository.SearchRepository
import javax.inject.Inject

/**
 * Search advert use case
 * @param searchRepository
 * @param coroutineDispatchers
 * @constructor Create empty Search advert use case
 */

class SearchAdvertUseCase @Inject constructor(
    private val searchRepository: SearchRepository,
    @IoDispatcher private val coroutineDispatchers: CoroutineDispatcher,
) {
    @OptIn(FlowPreview::class)
    suspend operator fun invoke(
        query: String,
        minPrice: String = "",
        maxPrice: String = "",
        propertyType: String = "",
        orderBy: String = "",
        orderDirection: String = "",
        minSize: String = "",
        maxSize: String = "",
        isShareRoom: String = "",
    ) = searchRepository.searchAdverts(
        query,
        minPrice,
        maxPrice,
        propertyType,
        orderBy,
        orderDirection,
        minSize,
        maxSize,
        isShareRoom
    ).debounce(300)
        .flowOn(coroutineDispatchers)

}