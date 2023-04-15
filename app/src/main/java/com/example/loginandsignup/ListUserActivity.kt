package com.example.loginandsignup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apirestcall.models.UserModel
import com.example.loginandsignup.adapter.UserAdapter
import com.example.loginandsignup.viewModels.UsersViewModel

class ListUserActivity : AppCompatActivity() {
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

        val search = findViewById<EditText>(R.id.etResearchedName)
        search.doAfterTextChanged {
            val listTemp = ArrayList<UserModel>()
            for (user in list) {
                if (user.FirstName.contains(it.toString(), true)) {
                    listTemp.add(user)
                } else if (user.LastName.contains(it.toString(), true)) {
                    listTemp.add(user)
                }
            }
            mRecyclerView.adapter = UserAdapter(listTemp)

            if (it.toString().isEmpty()) {
                mRecyclerView.adapter = UserAdapter(list)
            }
        }
    }

    fun moreInfo(id: Int) {
        val intent = Intent(this, MoreInfo::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }
}