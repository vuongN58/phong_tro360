package ptit.vuong.phongtro.domain.model

data class RoomModel(
    val room: DetailedRoomModel,
    val relatedModels: List<RelatedModel>
)

data class DetailedRoomModel(
    val id: Int,
    val createdAt: String,
    val userId: Int,
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
    val thumbnail270: String,
    val description: String,
    val utilities: String,
    val images: List<String>,
    val photoModels: List<PhotoModel>,
    val url: String,
    val isSaved: Boolean
)

data class PhotoModel(
    val id: Int,
    val image: String,
    val thumbnail120: String,
    val thumbnail270: String,
    val thumbnail500: String,
    val isDefault: Int
)

data class RelatedModel(
    val id: Int,
    val createdAt: String,
    val userId: Int,
    val sourceId: String?,
    val sourceType: String?,
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
    val sex: String?,
    val isShareRoom: Int,
    val provinceId: Int,
    val districtId: Int,
    val adStatus: String,
    val totalView: Int,
    val platform: String,
    val thumbnail270: String
)