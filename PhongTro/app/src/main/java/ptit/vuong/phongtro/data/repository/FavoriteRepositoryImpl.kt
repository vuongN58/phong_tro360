package ptit.vuong.phongtro.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ptit.vuong.phongtro.data.local.AppRoomDatabase
import ptit.vuong.phongtro.domain.mapper.toAdvertModel
import ptit.vuong.phongtro.domain.mapper.toFavoriteEntity
import ptit.vuong.phongtro.domain.model.AdvertModel
import ptit.vuong.phongtro.domain.model.DetailedRoomModel
import ptit.vuong.phongtro.domain.repository.FavoriteRepository
import ptit.vuong.phongtro.extension.asResultFlow
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val database: AppRoomDatabase,
) : FavoriteRepository {

    override fun getFavoriteList(): Flow<Result<List<AdvertModel>>> =
        database.favoriteDao().getFavorite().map { favoriteList ->
            favoriteList.map { entity ->
                entity.toAdvertModel()
            }
        }.asResultFlow()

    override suspend fun addFavorite(item: DetailedRoomModel) {
        database.favoriteDao().insertFavorite(item.toFavoriteEntity())
    }

    override suspend fun removeFavorite(id: Int) = database.favoriteDao().deleteFavorite(id)

    override suspend fun isFavorite(id: Int): Boolean = database.favoriteDao().isFavorite(id)

}