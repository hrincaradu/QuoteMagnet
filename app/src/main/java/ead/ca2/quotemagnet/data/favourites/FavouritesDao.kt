package ead.ca2.quotemagnet.data.favourites

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ead.ca2.quotemagnet.data.favourites.FavouritesContract.FavouritesEntries.COLUMN_ID
import ead.ca2.quotemagnet.data.favourites.FavouritesContract.FavouritesEntries.TABLE_NAME
import ead.ca2.quotemagnet.data.favourites.model.DatabaseQuotationDto


@Dao
interface FavouritesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addQuotation(quotation : DatabaseQuotationDto)

    @Delete
    suspend fun deleteQuotation(quotation: DatabaseQuotationDto)

    @Query("SELECT * FROM $TABLE_NAME")
    fun getAllQuotations() : Flow<List<DatabaseQuotationDto>>

    @Query("SELECT * FROM $TABLE_NAME WHERE $COLUMN_ID = :id")
    fun getQuotationById(id: String): Flow<DatabaseQuotationDto?>

    @Query("DELETE FROM $TABLE_NAME")
    suspend fun deleteAllQuotations()

}