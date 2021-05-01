package com.fuzi.atm

import dagger.Binds
import dagger.Module

@Module
abstract class HelloWorldModule {
    @Binds
    abstract fun helloWorldCommand(command: HelloWorldCommand): Command
}