package com.fuzi.atm

import java.math.BigDecimal
import javax.inject.Inject

class Database {
    val accounts = HashMap<String, Account>()

    @Inject
    constructor()

    fun getAccount(username: String): Account {
        return accounts.computeIfAbsent(username) {
            Account(it)
        }
    }


    class Account(val username: String) {
        private var balance: BigDecimal = BigDecimal.ZERO

        fun balance() = balance
    }
}