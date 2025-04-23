package ead.ca2.quotemagnet.data.favourites

import kotlinx.coroutines.flow.Flow
import ead.ca2.quotemagnet.data.favourites.model.DatabaseQuotationDto

interface FavouritesDataSource {

    suspend fun addQuotation(quotation: DatabaseQuotationDto)

    suspend fun deleteQuotation(quotation: DatabaseQuotationDto)

    fun getAllQuotations(): Flow<List<DatabaseQuotationDto>>

    fun getQuotationById(id: String): Flow<DatabaseQuotationDto?>

    suspend fun deleteAllQuotations()

}