package com.fuzi.atm

import android.util.Log
import java.util.*
import javax.inject.Inject

class CommandRouter {
    private val commands: HashMap<String?, Command> = HashMap()

    @Inject
    constructor(helloWorldCommand: HelloWorldCommand) {
        commands[helloWorldCommand.key()] = helloWorldCommand
    }

    fun route(input: String): Command.Status? {
        val splitInput =
            split(input)
        if (splitInput.isEmpty()) {
            return invalidCommand(input)
        }
        val commandKey = splitInput[0]
        val command = commands[commandKey] ?: return invalidCommand(input)
        val status =
            command.handleInput(splitInput.subList(1, splitInput.size))
        if (status === Command.Status.INVALID) {
            Log.d(TAG, "$commandKey: invalid arguments")
        }
        return status
    }

    private fun invalidCommand(input: String): Command.Status {
        Log.d(TAG, String.format("couldn't understand \"%s\". please try again.", input))
        return Command.Status.INVALID
    }

    companion object {
        // Split on whitespace
        private fun split(string: String): List<String?> {
            val ret = string.split(" ")
            return ret;
        }
    }
}