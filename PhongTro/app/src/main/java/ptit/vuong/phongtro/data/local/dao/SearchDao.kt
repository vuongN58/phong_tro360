package ptit.vuong.phongtro.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ptit.vuong.phongtro.data.local.entity.SearchHistoryEntity

@Dao
interface SearchDao {

    @Query("SELECT * FROM SearchHistoryEntity ORDER BY time DESC")
    fun getHistory() : Flow<List<SearchHistoryEntity>>

    @Insert
    suspend fun insertHistory(historyEntity: SearchHistoryEntity)

}