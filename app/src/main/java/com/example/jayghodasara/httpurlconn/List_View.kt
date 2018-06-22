package com.example.jayghodasara.httpurlconn

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_list__view.*
import org.json.JSONArray
import org.json.JSONObject
import java.net.URLDecoder
import org.json.JSONException


class List_View : AppCompatActivity() {


    lateinit var jsonobj: JSONObject
    lateinit var jsonarray: JSONArray
    lateinit var jsonstring: String
    var id: String? = null
    var name: String? = null
    var email: String? = null
    var address: String? = null
    var gender: String? = null
    var mobile: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list__view)


        jsonstring = intent.extras["values"].toString()


        jsonobj = JSONObject(jsonstring)
        Log.i("List", "passed")
        jsonarray = jsonobj.getJSONArray("contacts")

        var arraylist: ArrayList<pojo> = ArrayList()
        var count = 0


        while (count < jsonarray.length()) {

            var jo: JSONObject = jsonarray.getJSONObject(count)


            id = jo.getString("id")
            name = jo.getString("name")
            email = jo.getString("email")
            address = jo.getString("address")
            gender = jo.getString("gender")
            mobile = jo.getJSONObject("phone").getString("mobile")

            var p: pojo = pojo()

            p.id = id
            p.name = name
            p.email = email
            p.address = address
            p.gender = gender
            p.mobile = mobile

            arraylist.add(p)
            count++
        }


        var adap: adapter = adapter(this, arraylist)
        var layoutman = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        list.adapter = adap
        list.layoutManager = layoutman
    }
}
