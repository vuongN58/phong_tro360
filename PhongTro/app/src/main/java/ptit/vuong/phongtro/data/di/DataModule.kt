package ptit.vuong.phongtro.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ptit.vuong.phongtro.data.repository.AdvertRepositoryImpl
import ptit.vuong.phongtro.data.repository.ArticleRepositoryImpl
import ptit.vuong.phongtro.data.repository.FavoriteRepositoryImpl
import ptit.vuong.phongtro.data.repository.ProfileRepositoryImpl
import ptit.vuong.phongtro.data.repository.RoomRepositoryImpl
import ptit.vuong.phongtro.data.repository.SearchHistoryRepositoryImpl
import ptit.vuong.phongtro.data.repository.SearchRepositoryImpl
import ptit.vuong.phongtro.domain.repository.AdvertRepository
import ptit.vuong.phongtro.domain.repository.ArticleRepository
import ptit.vuong.phongtro.domain.repository.FavoriteRepository
import ptit.vuong.phongtro.domain.repository.ProfileRepository
import ptit.vuong.phongtro.domain.repository.RoomRepository
import ptit.vuong.phongtro.domain.repository.SearchHistoryRepository
import ptit.vuong.phongtro.domain.repository.SearchRepository

@Module
@InstallIn(SingletonComponent::class)
internal interface DataModule {

    @Binds
    fun bindArticleRepository(repositoryImpl: ArticleRepositoryImpl): ArticleRepository

    @Binds
    fun bindAdvertRepository(repositoryImpl: AdvertRepositoryImpl): AdvertRepository

    @Binds
    fun bindRoomRepository(repositoryImpl: RoomRepositoryImpl): RoomRepository

    @Binds
    fun bindSearchRepository(repositoryImpl: SearchRepositoryImpl): SearchRepository

    @Binds
    fun bindSearchHistoryRepository(repositoryImpl: SearchHistoryRepositoryImpl) : SearchHistoryRepository

    @Binds
    fun bindProfileRepository(repositoryImpl: ProfileRepositoryImpl) : ProfileRepository

    @Binds
    fun bindFavoriteRepository(repositoryImpl: FavoriteRepositoryImpl) : FavoriteRepository
}