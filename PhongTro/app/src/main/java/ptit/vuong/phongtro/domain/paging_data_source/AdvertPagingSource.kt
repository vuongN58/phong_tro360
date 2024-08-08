package ptit.vuong.phongtro.domain.paging_data_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ptit.vuong.phongtro.data.remote.RentRoomService
import ptit.vuong.phongtro.domain.mapper.toAdvertModel
import ptit.vuong.phongtro.domain.model.AdvertModel

/**
 * This class is used to implement the PagingSource interface
 * It is used to load data for the paging library
 * It contains the following properties: rentRoomService
 * @param rentRoomService: an instance of the RentRoomService interface
 */

class AdvertPagingSource(
    private val rentRoomService: RentRoomService
) : PagingSource<Int, AdvertModel>() {

    override fun getRefreshKey(state: PagingState<Int, AdvertModel>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AdvertModel> {
        val position = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = rentRoomService.getAdverts(start = position, length = params.loadSize)
            val advertList = response.adverts.map { it.toAdvertModel() }
            LoadResult.Page(
                data = advertList,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (advertList.isEmpty()) null else position + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    companion object {
        const val STARTING_PAGE_INDEX = 0
    }

}