package ead.ca2.quotemagnet.data.favourites

import ead.ca2.quotemagnet.domain.model.Quotation
import kotlinx.coroutines.flow.Flow

interface FavouritesRepository {

    suspend fun addQuotation(quotation: Quotation)

    suspend fun deleteQuotation(quotation: Quotation)

    fun getAllQuotations(): Flow<List<Quotation>>

    fun getQuotationById(id: String): Flow<Quotation?>

    suspend fun deleteAllQuotations()
}