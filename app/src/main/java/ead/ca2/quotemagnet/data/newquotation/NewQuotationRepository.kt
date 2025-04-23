package ead.ca2.quotemagnet.data.newquotation



import ead.ca2.quotemagnet.domain.model.Quotation

interface NewQuotationRepository {


    suspend fun getNewQuotation() : Result<Quotation>
}