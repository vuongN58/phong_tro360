package ptit.vuong.phongtro.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ptit.vuong.phongtro.data.di.IoDispatcher
import ptit.vuong.phongtro.domain.repository.ProfileRepository
import javax.inject.Inject

/**
 * Register account use case
 * @param profileRepository
 * @param dispatcher
 * @constructor Create empty Register account use case
 */

class RegisterAccountUseCase @Inject constructor(
    private val profileRepository: ProfileRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) {

    suspend operator fun invoke(
        email: String,
        password: String,
        confirmPassword: String,
        phone: String,
        fullName: String,
    ) = withContext(
        dispatcher
    ) {
        val lastName = fullName.split(" ").last()
        val remaining = fullName.removeSuffix(" $lastName")
        profileRepository.register(
            email = email,
            lastName = lastName,
            firstName = remaining,
            phone = phone,
            password = password,
            confirmPassword = confirmPassword
        )
    }

}