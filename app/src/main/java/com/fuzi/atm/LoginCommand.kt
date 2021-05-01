package com.fuzi.atm

import javax.inject.Inject

class LoginCommand @Inject constructor(val outputter: Outputter) : SingleArgCommand() {
    override fun key(): String? {
        return "login"
    }

    override fun handleArg(username: String?): Command.Result? {
        outputter.output(username + " is logged in")
        return Command.Result.handled()
    }
}