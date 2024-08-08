package ptit.vuong.phongtro.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ptit.vuong.phongtro.data.di.RentRoomServiceWithoutAuthQualifier
import ptit.vuong.phongtro.data.di.RentRoomWithoutAuthQualifier
import ptit.vuong.phongtro.data.remote.RentRoomService
import ptit.vuong.phongtro.domain.model.AdvertModel
import ptit.vuong.phongtro.domain.paging_data_source.AdvertPagingSource
import ptit.vuong.phongtro.domain.repository.AdvertRepository
import javax.inject.Inject

class AdvertRepositoryImpl @Inject constructor(
    @RentRoomServiceWithoutAuthQualifier private val rentRoomService: RentRoomService,
) : AdvertRepository {
    override suspend fun getAdvertsStream(): Flow<PagingData<AdvertModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                AdvertPagingSource(rentRoomService)
            }
        ).flow
    }
}