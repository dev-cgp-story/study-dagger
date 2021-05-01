package com.fuzi.atm

import android.util.Log
import javax.inject.Inject

class CommandRouter @Inject constructor(val commands: Map<String, @JvmSuppressWildcards Command>, val outputter: Outputter) {
    fun route(input: String): Command.Result? {
        val splitInput =
            split(input)
        if (splitInput.isEmpty()) {
            return invalidCommand(input)
        }
        val commandKey = splitInput[0]
        val command = commands[commandKey] ?: return invalidCommand(input)
        val result =
            command.handleInput(splitInput.subList(1, splitInput.size))
        if (result.status == Command.Status.INVALID) {
            outputter.output("$commandKey: invalid arguments")
        }
        return result
    }

    private fun invalidCommand(input: String): Command.Result {
        outputter.output(String.format("couldn't understand \"%s\". please try again.", input))
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