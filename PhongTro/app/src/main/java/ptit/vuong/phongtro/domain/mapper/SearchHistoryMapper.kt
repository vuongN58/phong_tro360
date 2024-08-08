package ptit.vuong.phongtro.domain.mapper

import ptit.vuong.phongtro.data.local.entity.SearchHistoryEntity
import ptit.vuong.phongtro.domain.model.SearchHistoryModel


/**
 * This function is used to convert a SearchHistoryEntity object to a SearchHistoryModel object
 */

fun SearchHistoryEntity.toModel(): SearchHistoryModel = SearchHistoryModel(
    content = content,
    time = time,
)

/**
 * This function is used to convert a SearchHistoryModel object to a SearchHistoryEntity object
 */

fun SearchHistoryModel.toEntity(): SearchHistoryEntity = SearchHistoryEntity(
    content = content,
    time = time,
)