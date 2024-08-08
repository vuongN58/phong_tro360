package ptit.vuong.phongtro.data.repository

import ptit.vuong.phongtro.data.di.RentRoomServiceWithoutAuthQualifier
import ptit.vuong.phongtro.data.di.RentRoomWithoutAuthQualifier
import ptit.vuong.phongtro.data.remote.RentRoomService
import ptit.vuong.phongtro.domain.mapper.toRoomModel
import ptit.vuong.phongtro.domain.model.RoomModel
import ptit.vuong.phongtro.domain.repository.RoomRepository
import ptit.vuong.phongtro.utils.runSuspendCatching
import javax.inject.Inject

class RoomRepositoryImpl @Inject constructor(
    @RentRoomServiceWithoutAuthQualifier private val roomService: RentRoomService,
) : RoomRepository {
    override suspend fun getRoomById(id: String): Result<RoomModel> {
        return runSuspendCatching {
            roomService.getRoomById(id).toRoomModel()
        }
    }
}