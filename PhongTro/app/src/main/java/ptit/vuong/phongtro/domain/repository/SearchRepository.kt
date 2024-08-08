package ptit.vuong.phongtro.domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ptit.vuong.phongtro.data.remote.response.AdvertResponse
import ptit.vuong.phongtro.domain.model.AdvertModel

interface SearchRepository {

    suspend fun searchAdverts(
        query: String,
        minPrice: String,
        maxPrice: String,
        propertyType: String,
        orderBy: String,
        orderDirection: String,
        minSize: String,
        maxSize: String,
        isShareRoom: String
    ): Flow<PagingData<AdvertModel>>
}