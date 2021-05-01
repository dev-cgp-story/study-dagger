package com.fuzi.atm

import javax.inject.Inject
import java.math.BigDecimal
import com.fuzi.atm.Database.Account

class DepositCommand @Inject constructor(
    val account: Account,
    val outputter: Outputter,
    val withdrawalLimiter: WithdrawalLimiter
) : BigDecimalCommand(outputter) {

    override fun key(): String {
        return "deposit"
    }

    override fun handleAmount(amount: BigDecimal) {
        account.deposit(amount)
        withdrawalLimiter.recordDeposit(amount)
        outputter.output("${account.username} now has: ${account.balance()}")
    }
}