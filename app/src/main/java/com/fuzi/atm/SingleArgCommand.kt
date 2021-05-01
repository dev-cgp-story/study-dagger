package com.fuzi.atm

/*
 * Copyright (C) 2019 The Dagger Authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.fuzi.atm.Command.Result

/** Abstract command that accepts a single argument.  */
abstract class SingleArgCommand : Command {
    override fun handleInput(input: List<String>): Result {
        return if (input.size == 1) handleArg(input[0]) else Result.invalid()
    }

    /** Handles the single argument to the command.  */
    protected abstract fun handleArg(arg: String): Result
}