package com.fuzi.atm

import android.util.Log
import javax.inject.Inject

class CommandRouter {
    private val commands: Map<String, @JvmSuppressWildcards Command>

    @Inject
    constructor(command: Map<String, @JvmSuppressWildcards Command>) {
        commands = command
    }

    fun route(input: String): Command.Result? {
        val splitInput =
            split(input)
        if (splitInput.isEmpty()) {
            return invalidCommand(input)
        }
        val commandKey = splitInput[0]
        val command = commands[commandKey] ?: return invalidCommand(input)
        val status =
            command.handleInput(splitInput.subList(1, splitInput.size))
        if (status === Command.Result.invalid()) {
            Log.d(TAG, "$commandKey: invalid arguments")
        }
        return status
    }

    private fun invalidCommand(input: String): Command.Result {
        Log.d(TAG, String.format("couldn't understand \"%s\". please try again.", input))
        return Command.Result.invalid()
    }

    companion object {
        // Split on whitespace
        private fun split(string: String): List<String> {
            val ret = string.split(" ")
            return ret;
        }
    }
}