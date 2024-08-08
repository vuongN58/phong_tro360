package ptit.vuong.phongtro.domain.mapper

import ptit.vuong.phongtro.data.local.entity.FavoriteEntity
import ptit.vuong.phongtro.data.remote.response.AdvertEntity
import ptit.vuong.phongtro.domain.model.AdvertModel

/**
 * This function is used to convert an AdvertEntity object to an AdvertModel object
 */

fun AdvertEntity.toAdvertModel() = AdvertModel(
    id = id,
    createdAt = createdAt,
    userId = userId,
    sourceId = sourceId,
    sourceType = sourceType,
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

fun FavoriteEntity.toAdvertModel() = AdvertModel(
    id = id,
    createdAt = createdAt,
    userId = userId,
    sourceId = sourceId,
    sourceType = sourceType,
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

fun AdvertModel.toFavoriteEntity() = FavoriteEntity(
    id = id,
    createdAt = createdAt,
    userId = userId,
    sourceId = sourceId,
    sourceType = sourceType,
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