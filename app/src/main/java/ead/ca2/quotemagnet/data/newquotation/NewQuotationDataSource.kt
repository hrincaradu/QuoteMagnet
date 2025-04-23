package ead.ca2.quotemagnet.data.newquotation

import retrofit2.Response
import ead.ca2.quotemagnet.data.newquotation.model.RemoteQuotationDto

interface NewQuotationDataSource {

    suspend fun getQuotation(lang : String): Response<RemoteQuotationDto>

}