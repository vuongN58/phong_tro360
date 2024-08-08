package ptit.vuong.phongtro.presentation.search

import androidx.paging.PagingData
import ptit.vuong.phongtro.domain.model.AdvertModel
import ptit.vuong.phongtro.domain.model.SearchHistoryModel

data class SearchUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = "",
    val searchData: PagingData<AdvertModel> = PagingData.empty(),
    val searchHistoryList : List<SearchHistoryModel> = emptyList(),
)