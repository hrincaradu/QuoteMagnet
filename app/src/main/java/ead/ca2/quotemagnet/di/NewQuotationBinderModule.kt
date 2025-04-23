package ead.ca2.quotemagnet.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ead.ca2.quotemagnet.data.newquotation.NewQuotationDataSource
import ead.ca2.quotemagnet.data.newquotation.NewQuotationDataSourceImpl
import ead.ca2.quotemagnet.data.newquotation.NewQuotationRepository
import ead.ca2.quotemagnet.data.newquotation.NewQuotationRepositoryImpl

@Module

@InstallIn(SingletonComponent::class)
abstract class NewQuotationBinderModule {

    @Binds
    abstract fun bindNewQuotationRepository(newQuotationRepositoryImpl: NewQuotationRepositoryImpl) : NewQuotationRepository

    @Binds
    abstract fun bindNewQuotationDataSource(newQuotationDataSourceImpl: NewQuotationDataSourceImpl) : NewQuotationDataSource
}