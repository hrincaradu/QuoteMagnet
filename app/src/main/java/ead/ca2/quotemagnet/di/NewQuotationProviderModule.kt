package ead.ca2.quotemagnet.di

import android.content.Context
import android.net.ConnectivityManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NewQuotationProviderModule {

    @Provides
    fun provideConnectivityChecker(@ApplicationContext context: Context): ConnectivityManager {
        return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager}

    @Provides
    @Singleton
    fun provideRetrofit() : Retrofit = Retrofit.Builder().baseUrl("https://quotationapi-2025-b8g6hvf0eza4d7bk.ukwest-01.azurewebsites.net/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

}