package ptit.vuong.phongtro.presentation.account

import ptit.vuong.phongtro.domain.model.ProfileModel

data class AccountUiState(
    val profile: ProfileModel? = null,
    val isLoading: Boolean = false,
    val isLoggedIn : Boolean = false,
    val error: String = ""
)