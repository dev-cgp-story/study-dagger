package com.fuzi.atm

import android.util.Log
import dagger.Module
import dagger.Provides

@Module
class SystemOutModule {
    @Provides
    fun textOutputter(): Outputter = object : Outputter {
        override fun output(output: String) {
            Log.d(TAG, output)
        }
    }
}