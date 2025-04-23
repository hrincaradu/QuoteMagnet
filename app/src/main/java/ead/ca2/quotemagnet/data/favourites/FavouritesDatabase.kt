package ead.ca2.quotemagnet.data.favourites

import androidx.room.Database
import androidx.room.RoomDatabase

import ead.ca2.quotemagnet.data.favourites.model.DatabaseQuotationDto

@Database(entities = [DatabaseQuotationDto::class], version = 1)
abstract class FavouritesDatabase : RoomDatabase() {
    abstract fun favouritesDao(): FavouritesDao
}