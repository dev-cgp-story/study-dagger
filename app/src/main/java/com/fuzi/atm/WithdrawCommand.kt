package com.fuzi.atm

import java.math.BigDecimal
import javax.inject.Inject

class WithdrawCommand @Inject constructor(
    val outputter: Outputter,
    val account: Database.Account,
    @MinimumBalance val minimumBalance: BigDecimal,
    @MaximumWithdrawal val maximumWithdrawal: BigDecimal
) : BigDecimalCommand(outputter) {

    override fun key(): String = "withdraw"

    override fun handleAmount(amount: BigDecimal) {
        if (amount.compareTo(maximumWithdrawal) > 0) {
            outputter.output("Failed (amount($amount).compareTo(maximumWithdrawal($maximumWithdrawal)) > 0)")
            return
        }

        val newBalance = account.balance().subtract(amount)
        if(newBalance.compareTo(minimumBalance) < 0) {
            outputter.output("Failed (newBalance($newBalance).compareTo(minimumBalance($minimumBalance)) < 0)")
            return
        }

        account.withdraw(amount)
        outputter.output("your new balance is: ${account.balance()}")
    }
}