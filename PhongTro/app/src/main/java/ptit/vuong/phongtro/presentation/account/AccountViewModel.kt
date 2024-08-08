package ptit.vuong.phongtro.presentation.account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ptit.vuong.phongtro.data.di.IoDispatcher
import ptit.vuong.phongtro.data.local.sharepref.SharePreferenceProvider
import ptit.vuong.phongtro.domain.model.ProfileModel
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val sharePreferenceProvider: SharePreferenceProvider,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : ViewModel() {
    private val _accountUiState: MutableStateFlow<AccountUiState> =
        MutableStateFlow(AccountUiState())
    val accountUiState = _accountUiState.asStateFlow()

    init {
        _accountUiState.update {
            it.copy(
                profile = sharePreferenceProvider.get(
                    SharePreferenceProvider.PROFILE,
                    ProfileModel.empty
                ),
                isLoggedIn = sharePreferenceProvider.get(SharePreferenceProvider.IS_LOGIN, false) ?: false
            )
        }
    }

    fun signOut() {
        viewModelScope.launch(ioDispatcher) {
            sharePreferenceProvider.save<Boolean>(SharePreferenceProvider.IS_LOGIN, false)
        }
    }

}