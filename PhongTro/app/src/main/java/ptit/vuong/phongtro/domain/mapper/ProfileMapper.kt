package ptit.vuong.phongtro.domain.mapper

import ptit.vuong.phongtro.data.remote.response.ProfileResponse
import ptit.vuong.phongtro.domain.model.ProfileModel

/**
 * This function is used to convert a ProfileResponse object to a ProfileModel object
 */

fun ProfileResponse.toModel(): ProfileModel =
    ProfileModel(
        id = user.id,
        email = user.email,
        name = user.name,
        phone = user.phone,
        avatar = user.image,
        token = token,
    )