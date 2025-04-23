package ead.ca2.quotemagnet.data.favourites.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ead.ca2.quotemagnet.data.favourites.FavouritesContract
import ead.ca2.quotemagnet.data.favourites.FavouritesContract.FavouritesEntries.TABLE_NAME

@Entity (tableName = TABLE_NAME)
data class DatabaseQuotationDto(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = FavouritesContract.FavouritesEntries.COLUMN_ID)
    val id: String,

    @ColumnInfo(name = FavouritesContract.FavouritesEntries.COLUMN_TEXT)
    val text: String,

    @ColumnInfo(name = FavouritesContract.FavouritesEntries.COLUMN_AUTHOR)
    val author: String


)