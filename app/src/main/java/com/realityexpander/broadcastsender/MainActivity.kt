package com.realityexpander.broadcastsender

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private var textView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.text_view)
    }

    fun sendBroadcast(v: View?) {
        val intent = Intent("com.realityexpander.EXAMPLE_ACTION")
        intent.setPackage("com.realityexpander.broadcastreceiver")
        val extras = Bundle()
        extras.putString("stringExtra", "Start")

        textView?.text = "Sending..."


        sendOrderedBroadcast(
            intent,
            null,
            // SenderReceiver(),

            // Using interface and abstract class (Works, but ugly)
//            object: SenderReceiver() {
//                override fun onResult(result: String) {
//                    textView?.text = result
//                }
//            },

            // Using lambda and abstract class (doesn't compile???)
//            object: SenderReceiver( { result ->
//                textView?.text = result
//            }),

            // Using lambda and Concrete class (Works)
            SenderReceiver { result ->
                textView?.text = result
            },
            null,
            0,
            "Start",
            extras
        )
    }
}