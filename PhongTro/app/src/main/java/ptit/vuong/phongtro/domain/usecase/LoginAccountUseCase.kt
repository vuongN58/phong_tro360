package ptit.vuong.phongtro.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ptit.vuong.phongtro.data.di.IoDispatcher
import ptit.vuong.phongtro.domain.repository.ProfileRepository
import javax.inject.Inject

/**
 * Login account use case
 * @param profileRepository
 * @param dispatcher
 * @constructor Create empty Login account use case
 */

class LoginAccountUseCase @Inject constructor(
    private val profileRepository: ProfileRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) {

    suspend operator fun invoke(email: String, password: String) = withContext(
        dispatcher
    ) {
        profileRepository.login(email, password)
    }

}
