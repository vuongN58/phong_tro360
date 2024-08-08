package ptit.vuong.phongtro.domain.repository

import ptit.vuong.phongtro.domain.model.RoomModel

interface RoomRepository {
    suspend fun getRoomById(id: String) : Result<RoomModel>
}