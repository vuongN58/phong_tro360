package ptit.vuong.phongtro.data.remote

import ptit.vuong.phongtro.data.remote.response.AdvertResponse
import ptit.vuong.phongtro.data.remote.response.ArticleResponse
import ptit.vuong.phongtro.data.remote.response.ProfileResponse
import ptit.vuong.phongtro.data.remote.response.RoomResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * This interface is used to define methods to interact with the API
 * The methods in this interface will be implemented by Retrofit
 * The methods in this interface will be used to get data from the API
 */

interface RentRoomService {

    @GET("articles")
    suspend fun getArticles(): ArticleResponse

    @GET("v2/adverts")
    suspend fun getAdverts(
        @Query(value = "start") start: Int = 0,
        @Query(value = "length") length: Int,
    ): AdvertResponse

    @GET("v2/adverts/{id}")
    suspend fun getRoomById(
        @Path(value = "id") id: String,
    ): RoomResponse

    @POST("v2/adverts/search")
    @FormUrlEncoded
    suspend fun searchAdverts(
        @Field("query") query: String,
        @Field("start") start: String,
        @Field("length") length: String,
        @Field("min_price") minPrice: String,
        @Field("max_price") maxPrice: String,
        @Field("property_type") propertyType: String,
        @Field("order_by") orderBy: String,
        @Field("order_direction") orderDirection: String,
        @Field("min_size") minSize: String,
        @Field("max_size") maxSize: String,
        @Field("is_share_room") isShareRoom: String
    ): AdvertResponse

    @POST("login")
    @FormUrlEncoded
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): ProfileResponse

    @POST("register")
    @FormUrlEncoded
    suspend fun register(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("confirm_password") confirmPassword: String,
        @Field("phone") phone: String,
        @Field("first_name") firstName: String,
        @Field("last_name") lastName: String
    ): ProfileResponse

}