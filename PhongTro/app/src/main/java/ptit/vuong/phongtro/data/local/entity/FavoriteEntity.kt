package ptit.vuong.phongtro.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity
data class FavoriteEntity(
    @PrimaryKey val id: Int,
    val createdAt: String,
    val userId: Int,
    val sourceId: String,
    val sourceType: String,
    val title: String,
    val address: String,
    val price: Int,
    val contactPhone: String,
    val contactName: String,
    val lat: String,
    val lng: String,
    val size: Int,
    val propertyType: String,
    val province: String,
    val district: String,
    val ward: String,
    val street: String,
    val image: String,
    val hasFurniture: Int,
    val advertType: String,
    val sex: String,
    val isShareRoom: Int,
    val provinceId: Int,
    val districtId: Int,
    val adStatus: String,
    val totalView: Int,
    val platform: String,
    val thumbnail270: String
)