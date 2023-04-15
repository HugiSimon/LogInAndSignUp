package com.example.appelwebexample.`class`

import com.squareup.moshi.Json

@Json
data class SignInRequest(
    val username: String,
    val password: String)
