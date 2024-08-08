package ptit.vuong.phongtro.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ptit.vuong.phongtro.data.di.IoDispatcher
import ptit.vuong.phongtro.domain.repository.RoomRepository
import javax.inject.Inject

/**
 * Get room by id use case
 * @param roomRepository
 * @param ioDispatcher
 * @constructor Create empty Get room by id use case
 */

class GetRoomByIdUseCase @Inject constructor(
    private val roomRepository: RoomRepository,
     @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) {
    suspend operator fun invoke(id: String) = withContext(ioDispatcher) {
        roomRepository.getRoomById(id)
    }
}