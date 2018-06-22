package com.example.jayghodasara.httpurlconn

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

open class MainActivity : AppCompatActivity() {

    lateinit var values: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        button.setOnClickListener(View.OnClickListener {

            json().execute("https://api.androidhive.info/contacts/")


        })


    }


    inner class json : AsyncTask<String, String, String>() {


        override fun doInBackground(vararg params: String?): String? {

            lateinit var httpURLConnection: HttpURLConnection
            lateinit var bufferedReader: BufferedReader


            var stringBuffer: StringBuffer

            try {

                var url: URL = URL(params[0])


                httpURLConnection = url.openConnection() as HttpURLConnection

                httpURLConnection.connect()

                var inputStream: InputStream = httpURLConnection.inputStream

                bufferedReader = BufferedReader(object : InputStreamReader(inputStream) {})

                stringBuffer = StringBuffer()
                var isDone = true

                //START-->Logic for parsing JSON
                while (isDone) {

                    var string = bufferedReader.readLine()
                    if (string != null) {
                        stringBuffer.append(string)
                    } else {
                        isDone = false
                    }


                }
                //END-->Logic for parsing JSON

                return stringBuffer.toString()

            } catch (m: MalformedURLException) {
                m.printStackTrace()
            } catch (i: IOException) {
                i.printStackTrace()
            } finally {
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect()
                }
                if (bufferedReader != null) {
                    bufferedReader.close()
                }
            }
            return null

        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            values = result!!
            var i: Intent = Intent(applicationContext, List_View::class.java)
            i.putExtra("values", values)
            startActivity(i)

        }


    }


}