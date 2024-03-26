package com.example.nasapictureoftheday

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {

    var spaceImageURL = ""
    var spaceExplaination = ""
    var spaceName = ""
    var spaceDate = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var button : Button = findViewById(R.id.button)
        var image: ImageView = findViewById(R.id.spaceimageView)

        getSpaceInfo(todayDate())
        Log.d("spaceImageURL", "space image URL set")
        button.setOnClickListener{
            var randomDate = randomDate()
            getSpaceInfo(randomDate)
        }

    }

    private fun getSpaceInfo(date: String){
        val client = AsyncHttpClient()
        var apiURL = "https://api.nasa.gov/planetary/apod?api_key=l1L6h8QWydxdinmqdt6e9drMxofYrkrQ8iYcKHhH&date=$date"

        client[apiURL, object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JsonHttpResponseHandler.JSON) {
                Log.d("space", "response successful$json")

                spaceImageURL = json.jsonObject.getString("hdurl")
                spaceExplaination = json.jsonObject.getString("explanation")
                spaceName = json.jsonObject.getString("title")
                spaceDate = json.jsonObject.getString("date")

                getImage()
            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                errorResponse: String,
                throwable: Throwable?
            ) {
                Log.d("Space Error", errorResponse)
            }
        }]
    }

    private fun getImage() {
        var explanation: TextView = findViewById(R.id.description)
        var name: TextView = findViewById(R.id.Name)
        var date: TextView = findViewById(R.id.date)

        explanation.text = spaceExplaination
        name.text = spaceName
        date.text = spaceDate

        Glide.with(this)
            .load(spaceImageURL)
            .fitCenter()
            .into(findViewById(R.id.spaceimageView))

    }

    private fun randomDate(): String{
        var calendar= Calendar.getInstance()
        calendar.set(1995, Calendar.JUNE, 16)

        var currentDate = Calendar.getInstance()

        var minMillis = calendar.timeInMillis
        var maxMillis = currentDate.timeInMillis
        var differenceMillis = maxMillis - minMillis

        val randomMillis = (Math.random() * differenceMillis).toLong()

        calendar.timeInMillis = minMillis + randomMillis

        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return sdf.format(calendar.time)
    }

    private fun todayDate(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return sdf.format(Date())
    }
}