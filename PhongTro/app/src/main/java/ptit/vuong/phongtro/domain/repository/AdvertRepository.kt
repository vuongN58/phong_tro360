package ptit.vuong.phongtro.domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ptit.vuong.phongtro.domain.model.AdvertModel

interface AdvertRepository {
    suspend fun getAdvertsStream(): Flow<PagingData<AdvertModel>>
}