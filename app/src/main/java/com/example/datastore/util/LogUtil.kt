package com.example.datastore.util

import android.util.Log
import java.lang.Exception
import java.lang.StringBuilder

/**
 * Created by Vijay on 10-01-2022.
 */

object LogUtil {
    private const val TAG = "dataStore"
    var isIsLogEnable = true
        private set
    private const val DETAIL_ENABLE = true

    private fun buildMsg(msg: String): String {
        val buffer = StringBuilder()
        if (DETAIL_ENABLE) {
            val stackTraceElement = Thread.currentThread().stackTrace[4]
            buffer.append("[ ")
            buffer.append(stackTraceElement.fileName)
            buffer.append(": ")
            buffer.append(stackTraceElement.methodName)
            buffer.append(": ")
            buffer.append(stackTraceElement.lineNumber)
        }
        buffer.append(" ] ")
        buffer.append(msg)
        return buffer.toString()
    }

    fun v(msg: String) {
        if (isIsLogEnable) {
            Log.v(TAG, buildMsg(msg))
        }
    }

    fun d(msg: String) {
        if (isIsLogEnable) {
            Log.d(TAG, buildMsg(msg))
        }
    }

    fun i(msg: String) {
        Log.i(TAG, buildMsg(msg))
    }

    fun w(msg: String) {
        Log.w(TAG, buildMsg(msg))
    }

    fun w(msg: String, e: Exception?) {
        Log.w(TAG, buildMsg(msg), e)
    }

    fun e(msg: String) {
        Log.e(TAG, buildMsg(msg))
    }

    fun e(msg: String, e: Exception?) {
        Log.e(TAG, buildMsg(msg), e)
    }

    fun setIsLogEnable(isLogEnable: Boolean) {
        isIsLogEnable = isLogEnable
    }
}