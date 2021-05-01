package com.fuzi.atm

import dagger.Component

@Component(modules = [LoginCommandModule::class, HelloWorldModule::class, SystemOutModule::class])
interface CommandRouterFactory {
    fun router(): CommandRouter
}