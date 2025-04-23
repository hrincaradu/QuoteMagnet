package ead.ca2.quotemagnet.di


import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ead.ca2.quotemagnet.data.favourites.FavouritesContract
import ead.ca2.quotemagnet.data.favourites.FavouritesDao
import ead.ca2.quotemagnet.data.favourites.FavouritesDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FavouritesProviderModule {

    @Provides @Singleton
    fun provideFavouritesDatabase(@ApplicationContext context: Context): FavouritesDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            FavouritesDatabase::class.java,
            FavouritesContract.DATABASE_NAME
        ).build()
    }
    @Provides
    @Singleton
    fun provideFavouritesDao(database: FavouritesDatabase): FavouritesDao {
        return database.favouritesDao()
    }

}