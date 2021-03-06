package com.example.otherlib

import android.annotation.SuppressLint
import android.view.SurfaceHolder
import androidx.annotation.VisibleForTesting
import com.example.otherlib.data.ONnvHandle
import kotlinx.coroutines.*
import java.util.*
import kotlin.properties.Delegates

object NnvManager {

    interface Listener {
        fun onFix(reqId: Int, resultCode: Int, handle: ONnvHandle)
    }

    private var reqId by Delegates.notNull<Int>()

    private val listeners = ArrayList<Listener>()

    fun addListener(listener: Listener) {
        listeners.add(listener)
    }

    fun removeListener(listener: Listener) {
        listeners.remove(listener)
    }

    fun create() : Int {
        reqId = Date().time.toInt()
        GlobalScope.launch {
            delay(100)
            listeners.forEach {
                it.onFix(reqId, 0, ONnvHandle(Date().time.toString()))
            }
        }
        return reqId
    }

}