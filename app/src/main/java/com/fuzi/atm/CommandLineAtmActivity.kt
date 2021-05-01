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

        val commands = arrayOf("hello", "login 강명신", "deposit 5000", "deposit 5000")
        val commandProcessor = DaggerCommandLineAtmActivity_CommandProcessorFactory.create().processor()

        for (command in commands) {
            commandProcessor.process(command)
        }
    }

    @Singleton
    @Component(modules = [HelloWorldModule::class, LoginCommandModule::class, SystemOutModule::class, UserCommandsRouter.InstallationModule::class])
    interface CommandProcessorFactory {
        fun processor(): CommandProcessor
    }
}