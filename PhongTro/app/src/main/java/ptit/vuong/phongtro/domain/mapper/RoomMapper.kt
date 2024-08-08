package ptit.vuong.phongtro.domain.mapper

import ptit.vuong.phongtro.data.local.entity.FavoriteEntity
import ptit.vuong.phongtro.data.remote.response.PhotoResponse
import ptit.vuong.phongtro.data.remote.response.RelatedResponse
import ptit.vuong.phongtro.data.remote.response.RoomDetailResponse
import ptit.vuong.phongtro.data.remote.response.RoomResponse
import ptit.vuong.phongtro.domain.model.DetailedRoomModel
import ptit.vuong.phongtro.domain.model.PhotoModel
import ptit.vuong.phongtro.domain.model.RelatedModel
import ptit.vuong.phongtro.domain.model.RoomModel

/**
 * This function is used to convert a RoomResponse object to a RoomModel object
 */

fun RoomResponse.toRoomModel(): RoomModel =
    RoomModel(
        room = this.roomDetailResponse.toDetailedRoomModel(),
        relatedModels = this.relatedResponses.map { it.toModel() }
    )

fun RoomDetailResponse.toDetailedRoomModel(): DetailedRoomModel {
    return DetailedRoomModel(
        id = this.id,
        createdAt = this.createdAt,
        userId = this.userId,
        title = this.title,
        address = this.address,
        price = this.price,
        contactPhone = this.contactPhone,
        contactName = this.contactName,
        lat = this.lat,
        lng = this.lng,
        size = this.size,
        propertyType = this.propertyType,
        province = this.province,
        district = this.district,
        ward = this.ward,
        street = this.street,
        image = this.image,
        hasFurniture = this.hasFurniture,
        advertType = this.advertType,
        sex = this.sex,
        isShareRoom = this.isShareRoom,
        provinceId = this.provinceId,
        districtId = this.districtId,
        adStatus = this.adStatus,
        totalView = this.totalView,
        platform = this.platform,
        thumbnail270 = this.thumbnail270,
        description = this.description,
        utilities = this.utilities,
        images = this.images,
        photoModels = this.photoResponses.map { it.toModel() },
        url = this.url,
        isSaved = this.isSaved
    )
}

fun DetailedRoomModel.toFavoriteEntity(): FavoriteEntity {
    return FavoriteEntity(
        id = id,
        createdAt = createdAt,
        userId = userId,
        sourceId = "",
        sourceType = "",
        title = title,
        address = address,
        price = price,
        contactPhone = contactPhone,
        contactName = contactName,
        lat = lat,
        lng = lng,
        size = size,
        propertyType = propertyType,
        province = province,
        district = district,
        ward = ward,
        street = street,
        image = image,
        hasFurniture = hasFurniture,
        advertType = advertType,
        sex = sex,
        isShareRoom = isShareRoom,
        provinceId = provinceId,
        districtId = districtId,
        adStatus = adStatus,
        totalView = totalView,
        platform = platform,
        thumbnail270 = thumbnail270
    )
}

fun PhotoResponse.toModel(): PhotoModel {
    return PhotoModel(
        id = this.id,
        image = this.image,
        thumbnail120 = this.thumbnail120,
        thumbnail270 = this.thumbnail270,
        thumbnail500 = this.thumbnail500,
        isDefault = this.isDefault
    )
}

fun RelatedResponse.toModel(): RelatedModel {
    return RelatedModel(
        id = this.id,
        createdAt = this.createdAt,
        userId = this.userId,
        sourceId = this.sourceId,
        sourceType = this.sourceType,
        title = this.title,
        address = this.address,
        price = this.price,
        contactPhone = this.contactPhone,
        contactName = this.contactName,
        lat = this.lat,
        lng = this.lng,
        size = this.size,
        propertyType = this.propertyType,
        province = this.province,
        district = this.district,
        ward = this.ward,
        street = this.street,
        image = this.image,
        hasFurniture = this.hasFurniture,
        advertType = this.advertType,
        sex = this.sex,
        isShareRoom = this.isShareRoom,
        provinceId = this.provinceId,
        districtId = this.districtId,
        adStatus = this.adStatus,
        totalView = this.totalView,
        platform = this.platform,
        thumbnail270 = this.thumbnail270
    )
}

