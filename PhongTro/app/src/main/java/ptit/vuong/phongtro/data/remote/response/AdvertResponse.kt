package ptit.vuong.phongtro.data.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AdvertEntity(
    @Json(name = "id") val id: Int,
    @Json(name = "created_at") val createdAt: String,
    @Json(name = "user_id") val userId: Int,
    @Json(name = "source_id") val sourceId: String,
    @Json(name = "source_type") val sourceType: String,
    @Json(name = "title") val title: String,
    @Json(name = "address") val address: String,
    @Json(name = "price") val price: Int,
    @Json(name = "contact_phone") val contactPhone: String,
    @Json(name = "contact_name") val contactName: String,
    @Json(name = "lat") val lat: String,
    @Json(name = "lng") val lng: String,
    @Json(name = "size") val size: Int,
    @Json(name = "property_type") val propertyType: String,
    @Json(name = "province") val province: String,
    @Json(name = "district") val district: String,
    @Json(name = "ward") val ward: String,
    @Json(name = "street") val street: String,
    @Json(name = "image") val image: String,
    @Json(name = "has_furniture") val hasFurniture: Int,
    @Json(name = "advert_type") val advertType: String,
    @Json(name = "sex") val sex: String,
    @Json(name = "is_share_room") val isShareRoom: Int,
    @Json(name = "province_id") val provinceId: Int,
    @Json(name = "district_id") val districtId: Int,
    @Json(name = "ad_status") val adStatus: String,
    @Json(name = "total_view") val totalView: Int,
    @Json(name = "platform") val platform: String,
    @Json(name = "thumbnail_270") val thumbnail270: String
)

@JsonClass(generateAdapter = true)
data class AdvertResponse(
    @Json(name = "status") val status: Boolean,
    @Json(name = "adverts") val adverts: List<AdvertEntity>
)