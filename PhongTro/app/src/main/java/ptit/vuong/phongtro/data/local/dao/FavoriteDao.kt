package ptit.vuong.phongtro.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ptit.vuong.phongtro.data.local.entity.FavoriteEntity

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM FavoriteEntity")
    fun getFavorite() : Flow<List<FavoriteEntity>>

    @Insert
    suspend fun insertFavorite(favoriteEntity: FavoriteEntity)

    @Query("DELETE FROM FavoriteEntity WHERE id = :id")
    suspend fun deleteFavorite(id: Int)

    @Query("SELECT EXISTS(SELECT * FROM FavoriteEntity WHERE id = :id)")
    suspend fun isFavorite(id: Int) : Boolean

}