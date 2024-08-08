package ptit.vuong.phongtro.data.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.asExecutor
import ptit.vuong.phongtro.data.local.AppRoomDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomDatabaseModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(
        @ApplicationContext context: Context,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): AppRoomDatabase {
        return AppRoomDatabase.getInstance(
            context = context,
            queryExecutor = dispatcher.asExecutor()
        )
    }

}