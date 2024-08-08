package ptit.vuong.phongtro.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ptit.vuong.phongtro.data.di.IoDispatcher
import ptit.vuong.phongtro.domain.model.AdvertModel
import ptit.vuong.phongtro.domain.model.DetailedRoomModel
import ptit.vuong.phongtro.domain.repository.FavoriteRepository
import ptit.vuong.phongtro.utils.runSuspendCatching
import javax.inject.Inject

class InsertFavoriteUseCase @Inject constructor(
    private val favoriteRepository: FavoriteRepository,
    @IoDispatcher private val coroutineDispatcher: CoroutineDispatcher,
) {
    suspend operator fun invoke(item: DetailedRoomModel) = runSuspendCatching {
        withContext(coroutineDispatcher) {
            favoriteRepository.addFavorite(item)
        }
    }
}
