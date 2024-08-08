package ptit.vuong.phongtro.domain.model

data class ProfileModel(
    val id: Int,
    val email: String,
    val name: String,
    val phone: String,
    val avatar: String,
    val token: String,
){
    companion object{
        val empty = ProfileModel(
            id = 0,
            email = "",
            name = "",
            phone = "",
            avatar = "",
            token = "",
        )
    }
}