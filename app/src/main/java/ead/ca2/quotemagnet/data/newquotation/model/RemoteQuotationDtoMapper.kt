package ead.ca2.quotemagnet.data.newquotation.model

import ead.ca2.quotemagnet.domain.model.Quotation
import retrofit2.Response
import java.io.IOException

fun RemoteQuotationDto.toDomain() : Quotation = Quotation(id = id, text = quoteText, author = quoteAuthor)


fun Response<RemoteQuotationDto>.toDomain() =
    if (isSuccessful)
        Result.success((body() as RemoteQuotationDto).toDomain())
    else Result.failure(IOException())