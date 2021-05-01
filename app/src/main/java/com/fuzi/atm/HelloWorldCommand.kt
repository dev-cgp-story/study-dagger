package com.fuzi.atm

import android.util.Log
import javax.inject.Inject

class HelloWorldCommand : Command {
    @Inject
    constructor()

    override fun key(): String? {
        return "hello"
    }

    override fun handleInput(input: List<String?>?): Command.Status? {
        if (!input!!.isEmpty()) {
            return Command.Status.INVALID
        }

        Log.d(TAG,"world!")
        return Command.Status.HANDLED
    }
}