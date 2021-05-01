package com.fuzi.atm

import android.util.Log
import javax.inject.Inject

class HelloWorldCommand @Inject constructor(val outputter: Outputter) : Command {

    override fun key(): String? {
        return "hello"
    }

    override fun handleInput(input: List<String?>?): Command.Status? {
        outputter.output("world!")
        return Command.Status.HANDLED
    }
}