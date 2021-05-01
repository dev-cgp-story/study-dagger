package com.fuzi.atm

import javax.inject.Inject

class LoginCommand @Inject constructor(
    val database: Database,
    val outputter: Outputter,
    val userCommandsRouterFactory: UserCommandsRouter.Factory
) : SingleArgCommand() {

    override fun key(): String {
        return "login"
    }

    override fun handleArg(username: String): Command.Result {
        val account = database.getAccount(username)

        outputter.output("$username is logged in with balance ${account.balance()}")
        return Command.Result.enterNestedCommandSet(userCommandsRouterFactory.create(account).router())
    }
}