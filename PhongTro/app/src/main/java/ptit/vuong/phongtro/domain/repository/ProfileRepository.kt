package ptit.vuong.phongtro.domain.repository

import ptit.vuong.phongtro.domain.model.ProfileModel

interface ProfileRepository {

    suspend fun login(email: String, password: String): Result<ProfileModel>

    suspend fun register(
        email: String,
        password: String,
        confirmPassword: String,
        phone: String,
        firstName: String,
        lastName: String,
    ): Result<ProfileModel>

}