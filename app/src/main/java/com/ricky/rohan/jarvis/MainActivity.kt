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
import co.intentservice.chatui.ChatView
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.github.kittinunf.result.getAs
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import co.intentservice.chatui.models.ChatMessage
import com.ricky.rohan.jarvis.R.id.message
import kotlinx.coroutines.experimental.channels.NULL_VALUE


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val chatView = findViewById<View>(R.id.chat_view) as ChatView
        var reply : String? = ""
        chatView.setOnSentMessageListener(object: ChatView.OnSentMessageListener {
            override fun sendMessage(chatMessage:ChatMessage):Boolean {
                // perform actual message sending
                Fuel.post("https://personalssistantv1.firebaseapp.com/api", listOf("data" to chatMessage.message)).responseString { request, response, result ->
                    val (value, error) = result
                    if (error == null) {
                        reply = value.toString()
                        //Toast.makeText(this@MainActivity,value.toString(), Toast.LENGTH_SHORT).show()
                        println(reply)
                        chatView.addMessage(ChatMessage(reply, System.currentTimeMillis(), ChatMessage.Type.RECEIVED))
                    } else {
                        //error handling
                    }
                }
                println(chatMessage.message)
                return true
            }
        })

        /*val edit = findViewById<View>(R.id.simpleEditText) as  EditText
        val button = findViewById<Button>(R.id.button) as Button
        val text = findViewById<View>(R.id.text) as TextView
        println(edit)
        button.setOnClickListener {
            Fuel.post("https://personalssistantv1.firebaseapp.com/api", listOf("data" to edit.getText().toString())).responseString{ request, response, result ->
                val (value, error) = result
                if (error == null) {
                    text.setText(value.toString())
                    //Toast.makeText(this@MainActivity,value.toString(), Toast.LENGTH_SHORT).show()
                    println(value)
                } else {
                    //error handling
                }
            }
            /*"https://crackiit.online/api".httpGet().responseString { request, response, result ->
                //make a GET to http://httpbin.org/get and do something with response
                val (data, error) = result
                if (error == null) {
                    text.setText(data)
                    println(data)
                } else {
                    //error handling
                }
            }*/

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
        }*/*/
        }
    }
/*async(CommonPool) {
                //val result = URL("http://personalssistantv1.firebaseapp.com/api").readText()
                val url = "https://crackiit.online/api"
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
                    Toast.makeText(this@MainActivity,response.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }*/
