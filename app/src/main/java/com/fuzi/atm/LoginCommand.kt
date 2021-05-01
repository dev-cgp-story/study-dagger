package com.fuzi.atm

import java.util.*
import javax.inject.Inject

class LoginCommand @Inject constructor(
    val database: Database,
    val outputter: Outputter,
    val userCommandsRouterFactory: UserCommandsRouter.Factory,
    val account: Optional<Database.Account>
) : SingleArgCommand() {

    override fun key(): String {
        return "login"
    }

    override fun handleArg(username: String): Command.Result {
        if(account.isPresent) {
            val loggedInUser = account.get().username
            outputter.output("$loggedInUser is already logged in")

            if(!loggedInUser.equals(username))
                outputter.output("run `logout` first before trying to log in another user")

            return Command.Result.handled()
        }

        val newAccount = database.getAccount(username)
        outputter.output("hello $username")
        return Command.Result.enterNestedCommandSet(userCommandsRouterFactory.create(newAccount).router())
    }
}