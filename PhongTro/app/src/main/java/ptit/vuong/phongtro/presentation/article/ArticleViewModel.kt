package ptit.vuong.phongtro.presentation.article

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ptit.vuong.phongtro.domain.usecase.GetArticlesUseCase
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(
    private val getArticlesUseCase: GetArticlesUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(ArticleUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            getArticlesUseCase().fold(
                onSuccess = { articles ->
                    _uiState.update {
                        it.copy(
                            articles = articles
                        )
                    }
                },
                onFailure = {
                    Log.e("ArticleViewModel", "init: $it")
                })
        }
    }
}