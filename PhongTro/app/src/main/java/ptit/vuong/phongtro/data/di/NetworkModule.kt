package ptit.vuong.phongtro.data.di

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import ptit.vuong.phongtro.data.remote.RentRoomService
import ptit.vuong.phongtro.di.OkHttpQualifierWithToken
import ptit.vuong.phongtro.di.OkHttpQualifierWithoutToken
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Qualifier
import javax.inject.Singleton

const val BASE_URL = "http://apixz92uv.thuenhatro360.com/api/"

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class RentRoomWithoutAuthQualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class RentRoomWithAuthQualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class RentRoomServiceWithoutAuthQualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class RentRoomServiceWithAuthQualifier

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @RentRoomWithoutAuthQualifier
    @Provides
    @Singleton
    fun provideRetrofit(
        moshi: Moshi,
        @OkHttpQualifierWithoutToken okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(okHttpClient)
        .build()

    @RentRoomWithAuthQualifier
    @Provides
    @Singleton
    fun provideRetrofitWithAuth(
        moshi: Moshi,
        @OkHttpQualifierWithToken okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(okHttpClient)
        .build()


    @RentRoomServiceWithoutAuthQualifier
    @Provides
    @Singleton
    fun provideRentRoomService(
        @RentRoomWithoutAuthQualifier retrofit: Retrofit
    ): RentRoomService = retrofit.create()

    @RentRoomServiceWithAuthQualifier
    @Provides
    @Singleton
    fun provideRentRoomServiceWithAuth(
        @RentRoomWithAuthQualifier retrofit: Retrofit
    ): RentRoomService = retrofit.create()

}