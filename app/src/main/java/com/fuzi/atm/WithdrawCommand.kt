package com.fuzi.atm

import java.math.BigDecimal
import javax.inject.Inject

class WithdrawCommand @Inject constructor(
    val outputter: Outputter,
    val account: Database.Account,
    @MinimumBalance val minimumBalance: BigDecimal,
    val withdrawalLimiter: WithdrawalLimiter
) : BigDecimalCommand(outputter) {

    override fun key(): String = "withdraw"

    override fun handleAmount(amount: BigDecimal) {
        val remainingWithdrawalLimit = withdrawalLimiter.remainingWithdrawalLimit()
        if (amount.compareTo(remainingWithdrawalLimit) > 0) {
            outputter.output("you may not withdraw $amount; you may withdraw $remainingWithdrawalLimit more in this session.")
            return
        }

        val newBalance = account.balance().subtract(amount)
        if(newBalance.compareTo(minimumBalance) < 0) {
            outputter.output("Failed (newBalance($newBalance).compareTo(minimumBalance($minimumBalance)) < 0)")
            return
        }

        account.withdraw(amount)
        withdrawalLimiter.recordWithdrawal(amount)
        outputter.output("your new balance is: ${account.balance()}")
    }
}