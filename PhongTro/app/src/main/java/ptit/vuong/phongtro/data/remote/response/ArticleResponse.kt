package ptit.vuong.phongtro.data.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ArticleResponse(
    @Json(name = "status") var status: Boolean = false,
    @Json(name = "articles") var result: List<ArticleEntity>? = null,
)

@JsonClass(generateAdapter = true)
data class ArticleEntity(
    @Json(name = "id") val id: Int,
    @Json(name = "user_id") val userId: Int,
    @Json(name = "title") val title: String,
    @Json(name = "image") val image: String,
    @Json(name = "short_description") val shortDescription: String,
    @Json(name = "total_comment") val totalComment: Int,
    @Json(name = "total_like") val totalLike: Int,
    @Json(name = "created_at") val createdAt: String,
    @Json(name = "url") val url: String
)