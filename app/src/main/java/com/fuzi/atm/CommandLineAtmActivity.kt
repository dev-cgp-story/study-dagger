package com.fuzi.atm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.Component
import javax.inject.Singleton

val TAG = "ATM App"

class CommandLineAtmActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()

        val commands = arrayOf("hello", "login 강명신", "deposit 1000", "withdraw 500", "logout", "login 박형원", "deposit 1000", "withdraw 999", "withdraw 2")
        val commandProcessor = DaggerCommandLineAtmActivity_CommandProcessorFactory.create().processor()

        for (command in commands) {
            commandProcessor.process(command)
        }
    }

    @Singleton
    @Component(modules = [HelloWorldModule::class, LoginCommandModule::class, SystemOutModule::class, UserCommandsRouter.InstallationModule::class, AmountsModule::class])
    interface CommandProcessorFactory {
        fun processor(): CommandProcessor
    }
}