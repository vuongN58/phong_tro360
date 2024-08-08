package ptit.vuong.phongtro.data.remote.response
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RoomResponse(
    @Json(name = "status") val status: Boolean,
    @Json(name = "advert") val roomDetailResponse: RoomDetailResponse,
    @Json(name = "relateds") val relatedResponses: List<RelatedResponse>
)

@JsonClass(generateAdapter = true)
data class RoomDetailResponse(
    @Json(name = "id") val id: Int,
    @Json(name = "created_at") val createdAt: String,
    @Json(name = "user_id") val userId: Int,
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
    @Json(name = "thumbnail_270") val thumbnail270: String,
    @Json(name = "description") val description: String,
    @Json(name = "utilities") val utilities: String,
    @Json(name = "images") val images: List<String>,
    @Json(name = "photos") val photoResponses: List<PhotoResponse>,
    @Json(name = "url") val url: String,
    @Json(name = "is_saved") val isSaved: Boolean
)

@JsonClass(generateAdapter = true)
data class PhotoResponse(
    @Json(name = "id") val id: Int,
    @Json(name = "image") val image: String,
    @Json(name = "thumbnail_120") val thumbnail120: String,
    @Json(name = "thumbnail_270") val thumbnail270: String,
    @Json(name = "thumbnail_500") val thumbnail500: String,
    @Json(name = "is_default") val isDefault: Int
)

@JsonClass(generateAdapter = true)
data class RelatedResponse(
    @Json(name = "id") val id: Int,
    @Json(name = "created_at") val createdAt: String,
    @Json(name = "user_id") val userId: Int,
    @Json(name = "source_id") val sourceId: String?,
    @Json(name = "source_type") val sourceType: String?,
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
    @Json(name = "sex") val sex: String?,
    @Json(name = "is_share_room") val isShareRoom: Int,
    @Json(name = "province_id") val provinceId: Int,
    @Json(name = "district_id") val districtId: Int,
    @Json(name = "ad_status") val adStatus: String,
    @Json(name = "total_view") val totalView: Int,
    @Json(name = "platform") val platform: String,
    @Json(name = "thumbnail_270") val thumbnail270: String
)