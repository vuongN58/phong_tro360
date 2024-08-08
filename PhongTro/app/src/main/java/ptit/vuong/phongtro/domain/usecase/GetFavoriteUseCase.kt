package ptit.vuong.phongtro.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flowOn
import ptit.vuong.phongtro.data.di.IoDispatcher
import ptit.vuong.phongtro.domain.repository.FavoriteRepository
import javax.inject.Inject

class GetFavoriteUseCase @Inject constructor(
    private val favoriteRepository: FavoriteRepository,
    @IoDispatcher private val coroutineDispatcher: CoroutineDispatcher,
) {

    operator fun invoke() = favoriteRepository.getFavoriteList().flowOn(coroutineDispatcher)

}