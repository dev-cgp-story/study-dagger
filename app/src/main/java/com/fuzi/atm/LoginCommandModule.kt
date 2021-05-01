package com.fuzi.atm

import dagger.Binds
import dagger.BindsOptionalOf
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey

@Module
abstract class LoginCommandModule {
    @Binds
    @IntoMap
    @StringKey("login")
    abstract fun loginCommand(command: LoginCommand): Command

    @BindsOptionalOf
    abstract fun optionalAccount(): Database.Account
}