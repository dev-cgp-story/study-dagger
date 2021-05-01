package com.fuzi.atm

import java.util.ArrayDeque
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CommandProcessor {
    val commandRouterStack = ArrayDeque<CommandRouter>()

    @Inject
    constructor(firstCommandRouter: CommandRouter) {
        commandRouterStack.push(firstCommandRouter)
    }

    fun process(input: String): Command.Status {
        val result = commandRouterStack.peek().route(input)
        if(result!!.status == Command.Status.INPUT_COMPLETED) {
            commandRouterStack.pop()
            return if (commandRouterStack.isEmpty()) Command.Status.INPUT_COMPLETED else Command.Status.HANDLED
        }

        result.nestedCommandRouter.ifPresent(commandRouterStack::push)
        return result.status
    }
}