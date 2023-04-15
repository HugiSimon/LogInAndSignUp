package com.example.loginandsignup.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.appelwebexample.`class`.SignInRequest
import com.example.appelwebexample.`class`.User
import kotlinx.coroutines.launch

class UsersViewModel : ViewModel() {
    val users = MutableLiveData<List<User>>()
    val currentUser = MutableLiveData<User>()
    val auth = MutableLiveData<String>()

    private val _status = MutableLiveData<String>()

    fun getUsers() {
        viewModelScope.launch {
            try {
                users.value = UserApi.retrofitService.getUsers()
                _status.value = "Success:  Users retrieved"
            } catch (e: Exception) {
                _status.value = "Failure: ${e.message}"
            }
        }
    }

    fun getUser(id: String) {
        viewModelScope.launch {
            try {
                currentUser.value = UserApi.retrofitService.getUser(id)
                _status.value = "Success:  Users retrieved"
            } catch (e: Exception) {
                _status.value = "Failure: ${e.message}"
            }
        }
    }

    fun signIn(user: String, password: String) {
        viewModelScope.launch {
            try {
                auth.value = UserApi.retrofitService.signIn(SignInRequest(user, password))
                _status.value = "Success:  Users retrieved"
            } catch (e: Exception) {
                _status.value = "Failure: ${e.message}"
            }
        }
    }
}