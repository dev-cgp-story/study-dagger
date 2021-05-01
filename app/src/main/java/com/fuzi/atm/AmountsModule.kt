package com.fuzi.atm

import dagger.Module
import dagger.Provides
import java.math.BigDecimal

@Module
class AmountsModule {
    @Provides
    @MinimumBalance
    fun minimumBalance() = BigDecimal.ZERO

    @Provides
    @MaximumWithdrawal
    fun maximumWithdrawal() = BigDecimal(1000)
}