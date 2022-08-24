package com.realityexpander.broadcastsender

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

// Video Series: // Video series: https://www.youtube.com/watch?v=prueLyTvOwI&list=PLrnPJCHvNZuBqr_0AS9BPXgU6gvNeai5S

class MainActivity : AppCompatActivity() {
    private var textView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.text_view)
    }

    fun sendBroadcast(v: View?) {
        val intent = Intent("com.realityexpander.EXAMPLE_ACTION")
        intent.setPackage("com.realityexpander.broadcastreceiver") // limits scope to our app only
        val extras = Bundle()
        extras.putString("stringExtra", "Start")

        textView?.text = "Sending..."


        sendOrderedBroadcast(
            intent,
            null,

//            // Using interface and abstract class (Works, but ugly)
//            object: SenderReceiver() {
//                override fun onResult(result: String) {
//                    textView?.text = result
//                }
//            },

//            // Using lambda and abstract class (Works, used for overloading)
//            object: SenderReceiver( { result ->
//                textView?.text = result
//            }){},

            // Using lambda and Concrete class (Cleanest, but cant override)
            SenderReceiver { result ->
                textView?.text = result
            },

            null,      // Make not-null to run on a thread other than the main thread
            0,        // `resultCode` in the receiver
            "Start",  // `resultData` in the receiver
            extras              // `getResultExtras` in the receiver
        )
    }
}