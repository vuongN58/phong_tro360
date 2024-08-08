package ptit.vuong.phongtro.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ptit.vuong.phongtro.domain.usecase.RegisterAccountUseCase
import ptit.vuong.phongtro.utils.EventChannel
import ptit.vuong.phongtro.utils.HasEventFlow
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerAccountUseCase: RegisterAccountUseCase,
    private val eventChannel: EventChannel<RegisterEvent>,
) : ViewModel(), HasEventFlow<RegisterEvent> by eventChannel {

    fun register(
        email: String,
        password: String,
        confirmPassword: String,
        phone: String,
        fullName: String,
    ) {
        viewModelScope.launch {
            busEvent(RegisterEvent.ShowLoading)
            registerAccountUseCase(
                email = email,
                password = password,
                confirmPassword = confirmPassword,
                phone = phone,
                fullName = fullName,
            ).onSuccess {
                busEvent(RegisterEvent.RegisterSuccess)
            }
                .onFailure {
                    busEvent(RegisterEvent.HideLoading)
                    busEvent(RegisterEvent.ShowError(it.message ?: "Unknown error"))
                }
        }
    }

    private fun busEvent(event: RegisterEvent) {
        viewModelScope.launch {
            eventChannel.send(event)
        }
    }
}