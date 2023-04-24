package com.example.loginandsignup

import androidx.appcompat.app.AppCompatActivity
import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MoreInfo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_more_info)

        val id = intent.getIntExtra("id", 0)

        val ivProfile = findViewById<ImageView>(R.id.ivProfile)
        val tvId = findViewById<TextView>(R.id.tvId)
        val tvFirstName = findViewById<TextView>(R.id.tvFirstName)
        val tvLastName = findViewById<TextView>(R.id.tvLastName)
        val tvEmail = findViewById<TextView>(R.id.tvEmail)
        val tvBirthday = findViewById<TextView>(R.id.tvBirthday)
        val tvGender = findViewById<TextView>(R.id.tvGender)
        val tvStreet = findViewById<TextView>(R.id.tvStreet)
        val tvCity = findViewById<TextView>(R.id.tvCity)
        val tvZipcode = findViewById<TextView>(R.id.tvZipcode)
        val tvCompanyName = findViewById<TextView>(R.id.tvCompanyName)
        val tvCompanyDescription = findViewById<TextView>(R.id.tvCompanyDescription)
        val tvCompanyStreet = findViewById<TextView>(R.id.tvCompanyStreet)
        val tvCompanyCity = findViewById<TextView>(R.id.tvCompanyCity)
        val tvCompanyZipcode = findViewById<TextView>(R.id.tvCompanyZipcode)

        GlobalScope.launch {
            val user = UserApi.retrofitService.getUser(id.toString())
            Log.d("User", user.toString())

            runOnUiThread {
                Glide.with(this@MoreInfo)
                    .load(user.avatar)
                    .into(ivProfile)
                tvId.text = "ID: ${user.id}"
                tvFirstName.text = user.firstname
                tvLastName.text = user.lastname
                tvEmail.text = user.email

                var birthday = ""
                birthday += when (user.birthdate.substring(5,7).toInt()) {
                    1 -> "Jan."
                    2 -> "Feb."
                    3 -> "Mar."
                    4 -> "Apr."
                    5 -> "May"
                    6 -> "Jun."
                    7 -> "Jul."
                    8 -> "Aug."
                    9 -> "Sep."
                    10 -> "Oct."
                    11 -> "Nov."
                    12 -> "Dec."
                    else -> "Error"
                }
                birthday += " ${user.birthdate.substring(8,10)}, ${user.birthdate.substring(0,4)} at ${user.birthdate.substring(11,16)}"
                tvBirthday.text = birthday

                tvGender.text = user.genre
                tvStreet.text = user.address.street
                tvCity.text = user.address.city
                tvZipcode.text = user.address.zipcode
                tvCompanyName.text = user.company.name
                tvCompanyDescription.text = user.company.description
                tvCompanyStreet.text = user.company.address.street
                tvCompanyCity.text = user.company.address.city
                tvCompanyZipcode.text = user.company.address.zipcode
            }
        }
    }
}