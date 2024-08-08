package ptit.vuong.phongtro.data.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProfileResponse(
    @Json(name = "status") val status: Boolean,
    @Json(name = "user") val user: UserEntity,
    @Json(name = "token") val token: String,
    @Json(name = "socket_token") val socketToken: String,
    @Json(name = "app_public_key") val appPublicKey: String?
)

@JsonClass(generateAdapter = true)
data class UserEntity(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "email") val email: String,
    @Json(name = "created_at") val createdAt: String,
    @Json(name = "updated_at") val updatedAt: String,
    @Json(name = "first_name") val firstName: String,
    @Json(name = "last_name") val lastName: String,
    @Json(name = "image") val image: String,
    @Json(name = "phone") val phone: String,
    @Json(name = "cover") val cover: String,
    @Json(name = "total_post") val totalPost: Int,
    @Json(name = "small_avatar") val smallAvatar: String,
    @Json(name = "alias") val alias: String,
    @Json(name = "status") val status: String,
    @Json(name = "mobichat_id") val mobichatId: String,
    @Json(name = "mobichat_token") val mobichatToken: String,
    @Json(name = "role") val role: String,
    @Json(name = "circle_thumbnail") val circleThumbnail: String,
    @Json(name = "thumbnail_500") val thumbnail500: String,
    @Json(name = "cover_1024") val cover1024: String,
    @Json(name = "circle_500") val circle500: String,
    @Json(name = "circle_200") val circle200: String,
    @Json(name = "language") val language: String,
    @Json(name = "about_me") val aboutMe: String,
    @Json(name = "total_follower") val totalFollower: Int,
    @Json(name = "total_following") val totalFollowing: Int,
    @Json(name = "total_view") val totalView: Int,
    @Json(name = "site_id") val siteId: Int,
    @Json(name = "source") val source: String,
    @Json(name = "is_disable_ads") val isDisableAds: Int,
    @Json(name = "is_verified") val isVerified: Int,
    @Json(name = "country_id") val countryId: Int,
    @Json(name = "province_id") val provinceId: Int,
    @Json(name = "address") val address: String,
    @Json(name = "lat") val lat: String,
    @Json(name = "lng") val lng: String,
    @Json(name = "user_type") val userType: String,
    @Json(name = "log") val log: String,
    @Json(name = "is_organization") val isOrganization: Int,
    @Json(name = "identity_id") val identityId: String?,
    @Json(name = "tax_id") val taxId: String?
)