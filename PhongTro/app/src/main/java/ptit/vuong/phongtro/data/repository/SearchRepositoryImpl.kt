package ptit.vuong.phongtro.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ptit.vuong.phongtro.data.di.RentRoomServiceWithoutAuthQualifier
import ptit.vuong.phongtro.data.di.RentRoomWithoutAuthQualifier
import ptit.vuong.phongtro.data.remote.RentRoomService
import ptit.vuong.phongtro.domain.model.AdvertModel
import ptit.vuong.phongtro.domain.paging_data_source.SearchAdvertPagingSource
import ptit.vuong.phongtro.domain.repository.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    @RentRoomServiceWithoutAuthQualifier private val rentRoomService: RentRoomService
) : SearchRepository {
    override suspend fun searchAdverts(
        query: String,
        minPrice: String,
        maxPrice: String,
        propertyType: String,
        orderBy: String,
        orderDirection: String,
        minSize: String,
        maxSize: String,
        isShareRoom: String
    ): Flow<PagingData<AdvertModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                SearchAdvertPagingSource(
                    rentRoomService,
                    query,
                    minPrice,
                    maxPrice,
                    propertyType,
                    orderBy,
                    orderDirection,
                    minSize,
                    maxSize,
                    isShareRoom
                )
            }
        ).flow
    }

}