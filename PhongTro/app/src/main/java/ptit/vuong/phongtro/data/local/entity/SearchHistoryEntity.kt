package ptit.vuong.phongtro.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class SearchHistoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val content: String,
    val time: Long,
)