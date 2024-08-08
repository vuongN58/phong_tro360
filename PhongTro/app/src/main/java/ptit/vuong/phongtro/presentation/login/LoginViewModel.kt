package ptit.vuong.phongtro.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ptit.vuong.phongtro.data.local.sharepref.SharePreferenceProvider
import ptit.vuong.phongtro.domain.model.ProfileModel
import ptit.vuong.phongtro.domain.usecase.LoginAccountUseCase
import ptit.vuong.phongtro.utils.EventChannel
import ptit.vuong.phongtro.utils.HasEventFlow
import javax.inject.Inject

/**
 * Login screen ViewModel
 * @param loginAccountUseCase: Use case for login
 * @param sharePreferenceProvider: Share preference provider
 * @param eventChannel: Event channel for Login screen
 **/

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginAccountUseCase: LoginAccountUseCase,
    private val sharePreferenceProvider: SharePreferenceProvider,
    private val eventChannel: EventChannel<LoginEvent>,
) : ViewModel(), HasEventFlow<LoginEvent> by eventChannel {

    fun login(email: String, password: String) {
        viewModelScope.launch {
            busEvent(LoginEvent.ShowLoading)
            loginAccountUseCase(email, password).onSuccess { result ->
                sharePreferenceProvider.save<String>(
                    SharePreferenceProvider.ACCESS_TOKEN,
                    result.token
                )
                sharePreferenceProvider.save<ProfileModel>(SharePreferenceProvider.PROFILE, result)
                sharePreferenceProvider.save<Boolean>(SharePreferenceProvider.IS_LOGIN, true)
                busEvent(LoginEvent.NavigateToHome)
            }
                .onFailure {
                    busEvent(LoginEvent.HideLoading)
                    busEvent(LoginEvent.ShowError(it.message ?: "Unknown error"))
                }
        }
    }

    private fun busEvent(event: LoginEvent) {
        viewModelScope.launch {
            eventChannel.send(event)
        }
    }

}