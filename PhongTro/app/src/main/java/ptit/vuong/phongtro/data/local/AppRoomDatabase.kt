package ptit.vuong.phongtro.data.local

import android.content.Context
import androidx.annotation.AnyThread
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ptit.vuong.phongtro.data.local.dao.FavoriteDao
import ptit.vuong.phongtro.data.local.dao.SearchDao
import ptit.vuong.phongtro.data.local.entity.FavoriteEntity
import ptit.vuong.phongtro.data.local.entity.SearchHistoryEntity
import java.util.concurrent.Executor
import java.util.concurrent.Executors

private const val databaseVersion = 2
private const val databaseName = "search-history.db"

@Database(
    version = databaseVersion,
    entities = [SearchHistoryEntity::class, FavoriteEntity::class],
    exportSchema = false,
)
abstract class AppRoomDatabase : RoomDatabase() {
    abstract fun searchDao(): SearchDao
    abstract fun favoriteDao(): FavoriteDao

    companion object {
        // Use a separate thread for Room transactions to avoid deadlocks. This means that tests that run Room
        // transactions can't use testCoroutines.scope.runBlockingTest, and have to simply use runBlocking instead.
        @Volatile
        private var INSTANCE: AppRoomDatabase? = null

        // Double-checked locking pattern, to avoid the overhead of synchronization once the instance has been initialized.
        // See https://en.wikipedia.org/wiki/Double-checked_locking#Usage_in_Java
        // Executors.newSingleThreadExecutor() is used to run the database operations asynchronously on a background thread.
        // See https://developer.android.com/training/data-storage/room/async-queries#kotlin
        @AnyThread
        @JvmStatic
        fun getInstance(context: Context, queryExecutor: Executor): AppRoomDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppRoomDatabase::class.java,
                    databaseName,
                )
                    .fallbackToDestructiveMigration()
                    .setTransactionExecutor(Executors.newSingleThreadExecutor())
                    .setQueryExecutor(queryExecutor)
                    .build()
                    .also { INSTANCE = it }
            }
    }
}