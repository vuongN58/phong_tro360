package ptit.vuong.phongtro.presentation.room

import ptit.vuong.phongtro.domain.model.RoomModel

data class RoomUiState(
    val id : Int = 0,
    val data: RoomModel? = null,
    val isLoading: Boolean = true,
    val isFavorite : Boolean = false,
    val error: String = ""
)