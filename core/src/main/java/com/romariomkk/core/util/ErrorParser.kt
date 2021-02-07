package com.romariomkk.core.util

interface ErrorParser {
    fun parse(t: Throwable): String
}