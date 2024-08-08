package ptit.vuong.phongtro.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flowOn
import ptit.vuong.phongtro.data.di.IoDispatcher
import ptit.vuong.phongtro.domain.repository.AdvertRepository
import javax.inject.Inject

/**
 * Advert use case to get adverts
 * @param advertRepository
 * @param ioDispatcher
 * @constructor Create empty Get advert use case
 */

class GetAdvertUseCase @Inject constructor(
    private val advertRepository: AdvertRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke() = advertRepository.getAdvertsStream().flowOn(ioDispatcher)
}