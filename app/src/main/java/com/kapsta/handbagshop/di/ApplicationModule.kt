package com.kapsta.handbagshop.di

import com.kapsta.handbagshop.service.AuthService
import com.kapsta.handbagshop.service.ProductService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Singleton
    @Provides
    fun provideAuthService(): AuthService = AuthService("https://104417.erply.com/api/")

    @Singleton
    @Provides
    fun provideProductService(): ProductService =
        ProductService("https://api-pim-eu10.erply.com/v1/")
}