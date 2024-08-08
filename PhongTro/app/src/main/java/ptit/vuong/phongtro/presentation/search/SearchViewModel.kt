package ptit.vuong.phongtro.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ptit.vuong.phongtro.domain.model.SearchHistoryModel
import ptit.vuong.phongtro.domain.usecase.GetSearchHistoryUseCase
import ptit.vuong.phongtro.domain.usecase.InsertSearchHistoryUseCase
import ptit.vuong.phongtro.domain.usecase.SearchAdvertUseCase
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCase: SearchAdvertUseCase,
    private val insertSearchHistoryUseCase: InsertSearchHistoryUseCase,
    private val getSearchHistoryUseCase: GetSearchHistoryUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getSearchHistory()
    }

    private fun getSearchHistory() {
        getSearchHistoryUseCase().map { result ->
            result.onSuccess { searchHistoryList ->
                _uiState.update {
                    it.copy(searchHistoryList = searchHistoryList)
                }
            }
            result.onFailure { failureResult ->
                _uiState.update {
                    it.copy(isError = true, errorMessage = failureResult.message.toString())
                }
            }
        }
            .launchIn(viewModelScope)
    }

    fun searchAdverts(
        query: String,
        minPrice: String = "",
        maxPrice: String = "",
        propertyType: String = "",
        orderBy: String = "",
        orderDirection: String = "",
        minSize: String = "",
        maxSize: String = "",
        isShareRoom: String = "",
    ) {
        viewModelScope.launch {
            insertSearchHistoryUseCase(
                SearchHistoryModel(
                    content = query,
                    time = System.currentTimeMillis()
                )
            )
            searchUseCase(
                query,
                minPrice,
                maxPrice,
                propertyType,
                orderBy,
                orderDirection,
                minSize,
                maxSize,
                isShareRoom,
            ).cachedIn(viewModelScope).collect { data ->
                _uiState.update { uiState ->
                    uiState.copy(searchData = data)
                }
            }
        }
    }
}