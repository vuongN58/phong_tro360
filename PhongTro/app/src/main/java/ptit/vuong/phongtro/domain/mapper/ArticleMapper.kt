package ptit.vuong.phongtro.domain.mapper

import ptit.vuong.phongtro.data.remote.response.ArticleEntity
import ptit.vuong.phongtro.data.remote.response.ArticleResponse
import ptit.vuong.phongtro.domain.model.ArticleModel

/**
 * This function is used to convert an ArticleEntity object to an ArticleModel object
 */

fun ArticleEntity.toArticleModel() = ArticleModel(
    id = id,
    userId = userId,
    title = title,
    image = image,
    shortDescription = shortDescription,
    totalComment = totalComment,
    totalLike = totalLike,
    createdAt = createdAt,
    url = url
)