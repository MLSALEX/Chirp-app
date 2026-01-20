package com.alexmls.chirp.di

import com.alexmls.auth.presentation.di.authPresentationModule
import com.alexmls.chat.data.di.chatDataModule
import com.alexmls.chat.presentation.di.chatPresentationModule
import com.alexmls.core.data.di.coreDataModule
import com.alexmls.core.presentation.di.corePresentationModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(
            coreDataModule,
            authPresentationModule,
            appModule,
            chatPresentationModule,
            corePresentationModule,
            chatDataModule
        )
    }
}