package com.fuzi.atm

import java.util.*

interface Command {
    /**
     * String token that signifies this command should be selected (e.g.:
     * "deposit", "withdraw")
     */
    fun key(): String?

    /** Process the rest of the command's words and do something.  */
    fun handleInput(input: List<String?>?): Result?


    class Result(val status: Status , val nestedCommandRouter: Optional<CommandRouter>) {
        companion object {
            fun invalid(): Result {
                return Result(Status.INVALID, Optional.empty<CommandRouter>());
            }

            fun handled(): Result {
                return Result(Status.HANDLED, Optional.empty<CommandRouter>());
            }

            fun inputCompleted(): Result {
                return Result(Status.INPUT_COMPLETED, Optional.empty<CommandRouter>());
            }

            fun enterNestedCommandSet(nestedCommandRouter: CommandRouter): Result {
                return Result(Status.HANDLED, Optional.of(nestedCommandRouter));
            }
        }
    }

    enum class Status {
        INVALID, HANDLED, INPUT_COMPLETED
    }
}
