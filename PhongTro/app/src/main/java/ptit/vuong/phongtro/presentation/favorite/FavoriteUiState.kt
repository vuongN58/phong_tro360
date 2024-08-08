package ptit.vuong.phongtro.presentation.favorite

import ptit.vuong.phongtro.domain.model.AdvertModel

data class FavoriteUiState(
    val isLoading: Boolean = true,
    val data: List<AdvertModel> = emptyList(),
    val error: String = ""
)