package ptit.vuong.phongtro.domain.model

data class ArticleModel(
    val id: Int,
    val userId: Int,
    val title: String,
    val image: String,
    val shortDescription: String,
    val totalComment: Int,
    val totalLike: Int,
    val createdAt: String,
    val url: String
)