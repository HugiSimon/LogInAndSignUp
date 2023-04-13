package com.example.loginandsignup.viewModels

import android.text.TextUtils
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey

class ConnectionViewModel : ViewModel() {
    val username = MutableLiveData("")
    private val password = MutableLiveData("")
    val token = MutableLiveData("null")

    fun setUsername(data: String) {
        if (!TextUtils.isEmpty(data) && !TextUtils.equals(data, username.toString())) {
            username.value = data
        }
    }

    fun setPassword(data: String) {
        if (!TextUtils.isEmpty(data) && !TextUtils.equals(data, password.toString())) {
            password.value = hashPassword(data)
            Log.d("password", password.value.toString())
        }
    }

    private fun hashPassword(rawPassword: String): String {
        val plaintext: ByteArray = rawPassword.toByteArray()

        val keygen = KeyGenerator.getInstance("HmacSHA256")
        keygen.init(256)
        val key: SecretKey = keygen.generateKey()

        val cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING")
        cipher.init(Cipher.ENCRYPT_MODE, key)
        val ciphertext: ByteArray = cipher.doFinal(plaintext)

        return ciphertext.toString()
    }
}