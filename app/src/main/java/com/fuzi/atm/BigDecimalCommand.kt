package com.fuzi.atm

import java.math.BigDecimal

abstract class BigDecimalCommand(
    private val outputter: Outputter
) : SingleArgCommand() {

    abstract fun handleAmount(amount: BigDecimal)

    override fun handleArg(arg: String): Command.Result {
        val amount = tryParse(arg)
        if(amount == null) {
            outputter.output("$arg is not a valid number")
        }
        else if(amount.signum() <= 0){
            outputter.output("amount must be positive")
        }
        else {
            handleAmount(amount)
        }

        return Command.Result.handled()
    }

    fun tryParse(arg: String): BigDecimal? {
        try {
            return BigDecimal(arg)
        } catch (e: NumberFormatException) {
            return null
        }
    }
}