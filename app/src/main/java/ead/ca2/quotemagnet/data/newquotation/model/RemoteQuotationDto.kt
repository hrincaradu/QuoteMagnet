package ead.ca2.quotemagnet.data.newquotation.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RemoteQuotationDto(
    val id: String,
    val quoteText: String,
    val quoteAuthor: String,


)