package com.fuzi.atm

import javax.inject.Inject
import com.fuzi.atm.Command.Result
import java.math.BigDecimal

class DepositCommand @Inject constructor(val database: Database, val outputter: Outputter) : Command {
    override fun key(): String {
        return "deposit"
    }

    override fun handleInput(input: List<String>): Result {
        if(input.size != 2)
            return Command.Result.invalid()

        val account = database.getAccount(input[0])
        account.deposit(BigDecimal(input[1]))
        outputter.output("${account.username} now has: ${account.balance()}")

        return Command.Result.handled()
    }
}