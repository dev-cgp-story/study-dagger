package com.fuzi.atm

import dagger.Binds
import dagger.Module

@Module
abstract class LoginCommandModule {
    @Binds
    abstract fun loginCommand(command: LoginCommand): Command
}