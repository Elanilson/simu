package com.apkdoandroid.simuladorderoupas.utils
import kotlinx.coroutines.*

/**
 * This fun is used to execute code in background thread
 * @param onBackgroundThread () -> Unit
 * @param onMainThread () -> Unit
 */
fun runOnBackground(onMainThread: () -> Unit = {}, onBackgroundThread: suspend () -> Unit = {}): Job {
    return GlobalScope.launch(Dispatchers.IO) {
        runCatching {
            onBackgroundThread()
            withContext(Dispatchers.Main) {
                onMainThread()
            }
        }.onFailure {
            it.printStackTrace()
        }
    }
}