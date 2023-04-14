package com.example.appelwebexample.`class`

import com.squareup.moshi.Json

@Json
data class Address(
    val street: String,
    val city: String,
    val zipcode: String)
