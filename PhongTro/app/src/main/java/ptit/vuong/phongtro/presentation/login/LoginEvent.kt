package ptit.vuong.phongtro.presentation.login

/**
    * Event for Login screen
    * 4 states of Login screen
    * 1. NavigateToHome: Go to Home screen
    * 2. ShowLoading: Show loading dialog
    * 3. HideLoading: Hide loading dialog
    * 4. ShowError: Show error dialog
 **/

sealed class LoginEvent {
    data object NavigateToHome : LoginEvent()
    data object ShowLoading : LoginEvent()
    data object HideLoading : LoginEvent()
    data class ShowError(val errorText: String) : LoginEvent()
}