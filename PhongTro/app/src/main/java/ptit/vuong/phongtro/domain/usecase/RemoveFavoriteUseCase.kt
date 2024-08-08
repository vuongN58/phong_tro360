package ptit.vuong.phongtro.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ptit.vuong.phongtro.data.di.IoDispatcher
import ptit.vuong.phongtro.domain.repository.FavoriteRepository
import ptit.vuong.phongtro.utils.runSuspendCatching
import javax.inject.Inject

class RemoveFavoriteUseCase @Inject constructor(
    private val favoriteRepository: FavoriteRepository,
    @IoDispatcher private val coroutineDispatcher: CoroutineDispatcher,
) {
    suspend operator fun invoke(id: Int) = runSuspendCatching {
        withContext(coroutineDispatcher) {
            favoriteRepository.removeFavorite(id)
        }
    }
}