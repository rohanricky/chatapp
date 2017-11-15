package com.ricky.rohan.jarvis

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button) as Button
        button.setOnClickListener {
            val job = async(CommonPool) {
                //val result = URL("http://personalssistantv1.firebaseapp.com/api").readText()
                val url = "https://personalssistantv1.firebaseapp.com/api"
                val obj = URL(url)
                val response = StringBuffer()
                with(obj.openConnection() as HttpURLConnection, {
                    // optional default is GET
                    requestMethod = "GET"


                    println("\nSending 'GET' request to URL : $url")
                    println("Response Code : $responseCode")

                    BufferedReader(InputStreamReader(inputStream)).use {

                        var inputLine = it.readLine()
                        while (inputLine != null) {
                            inputLine = it.readLine()
                            response.append(inputLine)
                        }
                    }
                    println(response.toString())
                })
                runOnUiThread() {
                    Toast.makeText(this@MainActivity,response, Toast.LENGTH_SHORT).show()
                }
            }
        }
        /*fun SendMethod(view : View) {
            var req = findViewById<View>(R.id.simpleEditText) as EditText
            val url = "http://personalssistantv1.firebaseapp.com/api"
            val obj = URL(url)
            var x = findViewById<View>(R.id.textView) as TextView

            with(obj.openConnection() as HttpURLConnection, {
                // optional default is GET
                requestMethod = "GET"


                println("\nSending 'GET' request to URL : $url")
                println("Response Code : $responseCode")

                BufferedReader(InputStreamReader(inputStream)).use {
                    val response = StringBuffer()

                    var inputLine = it.readLine()
                    while (inputLine != null) {
                        inputLine = it.readLine()
                        response.append(inputLine)
                    }
                    x.setText("HI")
                }
            })
        }*/
    }
}

