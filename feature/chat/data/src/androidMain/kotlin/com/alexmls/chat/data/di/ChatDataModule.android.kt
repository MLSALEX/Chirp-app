package com.alexmls.chat.data.di

import com.alexmls.chat.data.lifecycle.AppLifecycleObserver
import com.alexmls.chat.data.network.ConnectionErrorHandler
import com.alexmls.chat.data.network.ConnectivityObserver
import com.alexmls.chat.data.notification.FirebasePushNotificationService
import com.alexmls.chat.database.DatabaseFactory
import com.alexmls.chat.domain.notification.PushNotificationService
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

actual val platformChatDataModule = module {
    single { DatabaseFactory(androidContext()) }
    singleOf(::AppLifecycleObserver)
    singleOf(::ConnectivityObserver)
    singleOf(::ConnectionErrorHandler)
    singleOf(::FirebasePushNotificationService) bind PushNotificationService::class
}