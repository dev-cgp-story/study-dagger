package com.fuzi.atm

import javax.inject.Inject

class LogoutCommand @Inject constructor(
    val outputter: Outputter,
    val account: Database.Account
): Command {
    override fun key(): String = "logout"

    override fun handleInput(input: List<String>): Command.Result {
        if(!input.isEmpty()) {
            Command.Result.invalid()
        }

        outputter.output("logged out ${account.username}")
        return Command.Result.inputCompleted()
    }

}