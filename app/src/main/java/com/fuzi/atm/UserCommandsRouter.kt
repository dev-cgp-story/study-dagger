package com.fuzi.atm

import dagger.BindsInstance
import dagger.Module
import dagger.Subcomponent

@Subcomponent(modules = [UserCommandsModule::class])
interface UserCommandsRouter {
    fun router(): CommandRouter

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance account: Database.Account): UserCommandsRouter
    }

    @Module(subcomponents = [UserCommandsRouter::class])
    interface InstallationModule {

    }
}