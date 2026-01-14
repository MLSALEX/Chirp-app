package com.alexmls.core.data.di

import com.alexmls.core.data.auth.DataStoreSessionStorage
import com.alexmls.core.data.auth.KtorAuthService
import com.alexmls.core.data.logging.KermitLogger
import com.alexmls.core.data.networking.HttpClientFactory
import com.alexmls.core.domain.auth.AuthService
import com.alexmls.core.domain.auth.SessionStorage
import com.alexmls.core.domain.logging.ChirpLogger
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformCoreDataModule: Module

val coreDataModule = module {
    includes(platformCoreDataModule)
    single<ChirpLogger> { KermitLogger }
    single {
        HttpClientFactory(get(), get()).create(get())
    }
    singleOf(::KtorAuthService) bind AuthService::class
    singleOf(::DataStoreSessionStorage) bind SessionStorage::class
}