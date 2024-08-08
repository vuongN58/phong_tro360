package ptit.vuong.phongtro.domain.paging_data_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ptit.vuong.phongtro.data.remote.RentRoomService
import ptit.vuong.phongtro.domain.mapper.toAdvertModel
import ptit.vuong.phongtro.domain.model.AdvertModel

/**
 * This class is used to implement the PagingSource interface
 * It is used to implement the search functionality for the paging library
 * It contains the following properties: rentRoomService, query, minPrice, maxPrice, propertyType, orderBy, orderDirection, minSize, maxSize, isShareRoom
 * @param rentRoomService: an instance of the RentRoomService interface
 * @param query: a string value to store the search query
 * @param minPrice: a string value to store the minimum price
 * @param maxPrice: a string value to store the maximum price
 * @param propertyType: a string value to store the property type
 * @param orderBy: a string value to store the order by
 * @param orderDirection: a string value to store the order direction
 * @param minSize: a string value to store the minimum size
 * @param maxSize: a string value to store the maximum size
 * @param isShareRoom: a string value to store whether the room is shared
 */

class SearchAdvertPagingSource(
    private val rentRoomService: RentRoomService,
    private val query: String,
    private val minPrice: String,
    private val maxPrice: String,
    private val propertyType: String,
    private val orderBy: String,
    private val orderDirection: String,
    private val minSize: String,
    private val maxSize: String,
    private val isShareRoom: String
) : PagingSource<Int, AdvertModel>() {

    override fun getRefreshKey(state: PagingState<Int, AdvertModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AdvertModel> {
        val position = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = rentRoomService.searchAdverts(
                query = query,
                start = position.toString(),
                length = params.loadSize.toString(),
                minPrice = minPrice,
                maxPrice = maxPrice,
                propertyType = propertyType,
                orderBy = orderBy,
                orderDirection = orderDirection,
                minSize = minSize,
                maxSize = maxSize,
                isShareRoom = isShareRoom
            )
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