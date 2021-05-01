package com.fuzi.atm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

val TAG = "ATM App"

class CommandLineAtmActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()

        val commands = arrayOf("hello", "deposit 강명신 10000", "login 강명신", "deposit 강명신 5000")

        val commandRouterFactory = DaggerCommandRouterFactory.create();
        val commandRouter = commandRouterFactory.router()
        for (command in commands) {
            commandRouter.route(command)
        }
    }
}