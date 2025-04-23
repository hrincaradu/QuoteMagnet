package ead.ca2.quotemagnet.data.favourites

import kotlinx.coroutines.flow.Flow
import ead.ca2.quotemagnet.data.favourites.model.DatabaseQuotationDto
import javax.inject.Inject

class FavouritesDataSourceImpl @Inject constructor(
    private val favouritesDao: FavouritesDao
) : FavouritesDataSource {
    override suspend fun addQuotation(quotation: DatabaseQuotationDto) {
        favouritesDao.addQuotation(quotation)
    }

    override suspend fun deleteQuotation(quotation: DatabaseQuotationDto) {
        favouritesDao.deleteQuotation(quotation)
    }

    override fun getAllQuotations(): Flow<List<DatabaseQuotationDto>> {
        return favouritesDao.getAllQuotations()
    }

    override fun getQuotationById(id: String): Flow<DatabaseQuotationDto?> {
        return favouritesDao.getQuotationById(id)
    }

    override suspend fun deleteAllQuotations() {
        favouritesDao.deleteAllQuotations()
    }


}