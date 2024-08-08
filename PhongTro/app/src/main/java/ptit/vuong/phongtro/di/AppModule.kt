package ptit.vuong.phongtro.di

import android.content.Context
import android.content.SharedPreferences
import android.view.ContextMenu
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import ptit.vuong.phongtro.data.local.sharepref.SharePreferenceProvider
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class OkHttpQualifierWithoutToken

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class OkHttpQualifierWithToken

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class TokenInterceptor


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @OkHttpQualifierWithoutToken
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    @OkHttpQualifierWithToken
    @Provides
    @Singleton
    fun provideOkHttpClientWithToken(
        @TokenInterceptor tokenInterceptor: Interceptor,
        ): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor(tokenInterceptor)
            .build()
    }

    @TokenInterceptor
    @Provides
    @Singleton
    fun provideTokenInterceptor(
        @ApplicationContext context: Context,
        sharePreferenceProvider: SharePreferenceProvider,
    ): Interceptor {
        return Interceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader(
                    "Authorization",
                    "Bearer ${
                        sharePreferenceProvider.get(
                            SharePreferenceProvider.ACCESS_TOKEN,
                            ""
                        )
                    }"
                )
                .build()
            chain.proceed(request)
        }
    }


    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .add(KotlinJsonAdapterFactory())
        .build()


    @Provides
    @Singleton
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(
            SharePreferenceProvider.NAME_SHARE_PREFERENCE,
            Context.MODE_PRIVATE
        )
    }

}