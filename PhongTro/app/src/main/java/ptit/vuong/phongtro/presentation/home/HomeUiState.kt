package ptit.vuong.phongtro.presentation.home

import androidx.paging.PagingData
import ptit.vuong.phongtro.domain.model.AdvertModel

/**
    * This class is used to represent the state of the home screen
    * It contains the following properties: isLoading, isError, errorMessage, data
    * isLoading: a boolean value to indicate whether the data is being loaded
    * isError: a boolean value to indicate whether an error occurred while loading the data
    * errorMessage: a string value to store the error message
    * data: a PagingData object to store the list of adverts
 **/

data class HomeUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = "",
    val data: PagingData<AdvertModel> = PagingData.empty()
)