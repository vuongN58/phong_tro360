package ptit.vuong.phongtro.presentation.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import ptit.vuong.phongtro.domain.usecase.GetFavoriteUseCase
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getFavoriteUseCase: GetFavoriteUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(FavoriteUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getFavorite()
    }

    private fun getFavorite() {
        getFavoriteUseCase().map { result ->
            result.onSuccess { data ->
                _uiState.update {
                    it.copy(
                        data = data,
                        isLoading = false
                    )
                }
            }
            result.onFailure {
                _uiState.value = FavoriteUiState(error = it.message ?: "")
            }
        }.launchIn(viewModelScope)
    }
}