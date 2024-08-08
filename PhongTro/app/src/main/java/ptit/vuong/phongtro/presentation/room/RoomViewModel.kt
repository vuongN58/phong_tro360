package ptit.vuong.phongtro.presentation.room

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ptit.vuong.phongtro.domain.usecase.CheckIfIsFavoriteUseCase
import ptit.vuong.phongtro.domain.usecase.GetRoomByIdUseCase
import ptit.vuong.phongtro.domain.usecase.InsertFavoriteUseCase
import ptit.vuong.phongtro.domain.usecase.RemoveFavoriteUseCase
import ptit.vuong.phongtro.utils.AppConstant
import javax.inject.Inject

@HiltViewModel
class RoomViewModel @Inject constructor(
    private val getRoomByIdUseCase: GetRoomByIdUseCase,
    private val checkIfIsFavoriteUseCase: CheckIfIsFavoriteUseCase,
    private val removeFavoriteUseCase: RemoveFavoriteUseCase,
    private val insertFavoriteUseCase: InsertFavoriteUseCase,
    private val stateHandle: SavedStateHandle,
) : ViewModel() {

    private val _uiState = MutableStateFlow(RoomUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            stateHandle.get<Int>(AppConstant.KEY_ROOM_ID)?.let { id ->
                getRoomByIdUseCase(id.toString()).fold(
                    onSuccess = { result ->
                        _uiState.update {
                            it.copy(
                                id = id,
                                data = result,
                                isLoading = false,
                                isFavorite = checkIfIsFavoriteUseCase(id)
                            )
                        }
                    },
                    onFailure = {
                        _uiState.update {
                            it.copy(error = it.error, isLoading = false)
                        }
                    }
                )
            }
        }
    }

    fun setFavourite() {
        viewModelScope.launch {
            if (_uiState.value.isFavorite) {
                removeFavoriteUseCase(_uiState.value.id)
            } else {
                insertFavoriteUseCase(_uiState.value.data?.room ?: return@launch)
            }
            _uiState.update {
                it.copy(isFavorite = !it.isFavorite)
            }
        }
    }

}