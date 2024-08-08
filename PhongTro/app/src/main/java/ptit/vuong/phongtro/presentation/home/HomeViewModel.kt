package ptit.vuong.phongtro.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ptit.vuong.phongtro.domain.usecase.GetAdvertUseCase
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAdvertsUseCase: GetAdvertUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            getAdvertsUseCase().cachedIn(viewModelScope).collect { data ->
                _uiState.update { uiState ->
                    uiState.copy(data = data)
                }
            }
        }
    }

}