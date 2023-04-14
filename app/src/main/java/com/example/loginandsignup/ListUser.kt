package com.example.loginandsignup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apirestcall.models.UserModel
import com.example.loginandsignup.adapter.UserAdapter
import com.example.loginandsignup.viewModels.UsersViewModel

class ListUser : AppCompatActivity() {
    private lateinit var usersViewModel: UsersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_user)

        val mRecyclerView = findViewById<RecyclerView>(R.id.rvMain)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        val list = ArrayList<UserModel>()

        usersViewModel = ViewModelProvider(this)[UsersViewModel::class.java]
        usersViewModel.getUsers()

        usersViewModel.users.observe(this) {
            for (user in it) {
                list.add(UserModel(user.firstname, user.lastname, user.avatar))
            }
            mRecyclerView.adapter = UserAdapter(list)
        }
    }
}