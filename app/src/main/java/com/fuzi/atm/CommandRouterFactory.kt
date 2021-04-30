package com.fuzi.atm

import dagger.Component

@Component
interface CommandRouterFactory {
    fun router(): CommandRouter
}