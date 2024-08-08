package ptit.vuong.phongtro.presentation.register

/**
 * Register event
 */

sealed class RegisterEvent {
    data object RegisterSuccess : RegisterEvent()
    data object ShowLoading : RegisterEvent()
    data object HideLoading : RegisterEvent()
    data class ShowError(val errorText: String) : RegisterEvent()
}