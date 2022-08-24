package com.realityexpander.broadcastsender

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast


// For use with abstract class SenderReceiver
interface ResultListener {
    // Allows UI to update itself when the broadcast is received
    fun onResult(result: String)
}

//abstract class SenderReceiver : BroadcastReceiver(), ResultListener {
class SenderReceiver(private val onResult: ((String) -> Unit) ) : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        var resultCode = resultCode
        var resultData = resultData
        val resultExtras = getResultExtras(true)
        var stringExtra = resultExtras.getString("stringExtra")

        resultCode++
        stringExtra += "->SenderReceiver"
        val toastText = """
               SenderReceiver
               resultCode: $resultCode
               resultData: $resultData
               stringExtra: $stringExtra
               """.trimIndent()
        Toast.makeText(context, toastText, Toast.LENGTH_LONG).show()

        resultData = "SenderReceiver"
        resultExtras.putString("stringExtra", stringExtra)

        //setResult(resultCode, resultData, resultExtras)

        onResult(toastText)
    }
}