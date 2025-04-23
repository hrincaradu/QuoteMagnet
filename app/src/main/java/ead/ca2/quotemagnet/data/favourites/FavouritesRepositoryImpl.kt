package ead.ca2.quotemagnet.data.favourites

import ead.ca2.quotemagnet.domain.model.Quotation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ead.ca2.quotemagnet.data.favourites.model.toDatabaseDto
import ead.ca2.quotemagnet.data.favourites.model.toDomain
import javax.inject.Inject

class FavouritesRepositoryImpl @Inject constructor(
    private val favouritesDataSource: FavouritesDataSource
): FavouritesRepository {
    override suspend fun addQuotation(quotation: Quotation) {
        favouritesDataSource.addQuotation(quotation.toDatabaseDto())
    }

    override suspend fun deleteQuotation(quotation: Quotation) {
        favouritesDataSource.deleteQuotation(quotation.toDatabaseDto())
    }

    override fun getAllQuotations(): Flow<List<Quotation>> =
        favouritesDataSource.getAllQuotations().map {list->
            list.map {
                it.toDomain()
            }
        }

    override fun getQuotationById(id: String): Flow<Quotation?> =
        favouritesDataSource.getQuotationById(id)
            .map { it?.toDomain() }


    override suspend fun deleteAllQuotations() {
        favouritesDataSource.deleteAllQuotations()
    }
}