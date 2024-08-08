package ptit.vuong.phongtro.domain.repository

import kotlinx.coroutines.flow.Flow
import ptit.vuong.phongtro.domain.model.AdvertModel
import ptit.vuong.phongtro.domain.model.DetailedRoomModel

interface FavoriteRepository {

    fun getFavoriteList(): Flow<Result<List<AdvertModel>>>

    suspend fun addFavorite(item : DetailedRoomModel)

    suspend fun removeFavorite(id: Int)

    suspend fun isFavorite(id: Int) : Boolean

}