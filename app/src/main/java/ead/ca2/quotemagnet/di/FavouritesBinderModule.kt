package ead.ca2.quotemagnet.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ead.ca2.quotemagnet.data.favourites.FavouritesDataSource
import ead.ca2.quotemagnet.data.favourites.FavouritesDataSourceImpl
import ead.ca2.quotemagnet.data.favourites.FavouritesRepository
import ead.ca2.quotemagnet.data.favourites.FavouritesRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class FavouritesBinderModule {

    @Binds
    abstract fun bindFavouritesDataSource(favouritesDataSourceImpl: FavouritesDataSourceImpl): FavouritesDataSource

    @Binds
    abstract fun bindFavouritesRepository(favouritesRepositoryImpl: FavouritesRepositoryImpl): FavouritesRepository
}