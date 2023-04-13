package com.example.loginandsignup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.loginandsignup.fragment.LogIn
import com.example.loginandsignup.fragment.SignUp

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       login()
    }

    fun login() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.flMain, LogIn(), "login").commit()
    }

    fun signup() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.flMain, SignUp(), "signup").commit()
    }
}