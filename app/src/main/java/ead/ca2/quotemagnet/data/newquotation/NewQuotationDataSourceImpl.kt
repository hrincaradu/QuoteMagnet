package ead.ca2.quotemagnet.data.newquotation

import okhttp3.MediaType
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import ead.ca2.quotemagnet.data.newquotation.model.RemoteQuotationDto
import javax.inject.Inject

class NewQuotationDataSourceImpl @Inject constructor(
    retrofit: Retrofit
) : NewQuotationDataSource {

    interface NewQuotationRetrofit {
        @GET("api/1.0/?method=getQuote&format=json&")

        suspend fun getQuotation(@Query("lang") lang: String): Response<RemoteQuotationDto>
    }
    private val retrofitQuotationService = retrofit.create(NewQuotationRetrofit::class.java)

    override suspend fun getQuotation(lang : String): Response<RemoteQuotationDto> {
        return try {
            retrofitQuotationService.getQuotation(lang)
        } catch (e: Exception) {
            Response.error(
                400,
                ResponseBody.create(MediaType.parse("text/plain"), e.toString())
            )
        }
    }
}