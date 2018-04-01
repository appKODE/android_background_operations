package ru.rinekri.servicetest

import android.app.IntentService
import android.content.Intent
import android.os.Handler
import android.util.Log
import android.widget.Toast

class TimerHandlerService : IntentService(TAG) {
  companion object {
    private const val TAG = "TimerHandlerService"
  }

  private var uiHandler: Handler? = null

  override fun onCreate() {
    super.onCreate()
    Log.d(TAG, "onCreate")
    uiHandler = Handler()
    "$TAG: onCreate".showToast()
  }

  // NOTE: Нужно очищать ресурсы: потоки, ресурсы и т.д.
  // Иначе будут утечки - например, бесконечный цикл будет выполняться дальше.
  override fun onDestroy() {
    super.onDestroy()
    Log.d(TAG, "onDestroy")
    "$TAG: onDestroy".showToast()
  }

  override fun onHandleIntent(intent: Intent) {
    Log.d(TAG, "onHandleIntent")

    var second = 0
    while (true) {
      val msg = if (second == 0) {
        "$TAG: invoked"
      } else {
        "$TAG: $second seconds elapsed"
      }.also {
        it.showToast()
        Log.e(TAG, it)
      }
      second += 1
      Thread.sleep(1000L)
    }
  }

  private fun String.showToast(length: Int = Toast.LENGTH_SHORT) {
    uiHandler?.post { Toast.makeText(applicationContext, this, length).show() }
  }
}