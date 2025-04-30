package ead.ca2.quotemagnet.data.newquotation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import ead.ca2.quotemagnet.data.newquotation.model.toDomain
import ead.ca2.quotemagnet.data.settings.SettingsRepository
import ead.ca2.quotemagnet.domain.model.Quotation
import utils.NoInternetException
import javax.inject.Inject


class NewQuotationRepositoryImpl @Inject constructor(
    private val connectivityChecker: ConnectivityChecker,
    private val retrofitDataSource: NewQuotationDataSource,


): NewQuotationRepository {

    override suspend fun getNewQuotation(): Result<Quotation> {
        return if (connectivityChecker.isConnectionAvailable()) {
            retrofitDataSource.getQuotation().toDomain()
        }
        else {
            Result.failure(NoInternetException())
        }

    }
}