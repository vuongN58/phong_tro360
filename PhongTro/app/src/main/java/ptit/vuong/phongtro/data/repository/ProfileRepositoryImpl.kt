package ptit.vuong.phongtro.data.repository

import ptit.vuong.phongtro.data.di.RentRoomServiceWithoutAuthQualifier
import ptit.vuong.phongtro.data.di.RentRoomWithoutAuthQualifier
import ptit.vuong.phongtro.data.remote.RentRoomService
import ptit.vuong.phongtro.domain.mapper.toModel
import ptit.vuong.phongtro.domain.model.ProfileModel
import ptit.vuong.phongtro.domain.repository.ProfileRepository
import ptit.vuong.phongtro.utils.runSuspendCatching
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    @RentRoomServiceWithoutAuthQualifier private val rentRoomService: RentRoomService,
) : ProfileRepository {
    override suspend fun login(email: String, password: String): Result<ProfileModel> =
        runSuspendCatching {
            rentRoomService.login(email, password).toModel()
        }

    override suspend fun register(
        email: String,
        password: String,
        confirmPassword: String,
        phone: String,
        firstName: String,
        lastName: String,
    ): Result<ProfileModel> =
        runSuspendCatching {
            rentRoomService.register(
                email = email,
                password = password,
                confirmPassword = confirmPassword,
                phone = phone,
                firstName = firstName,
                lastName = lastName
            ).toModel()
        }
}