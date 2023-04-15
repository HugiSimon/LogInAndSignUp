package com.example.loginandsignup.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.apirestcall.models.UserModel
import com.example.loginandsignup.ListUserActivity
import com.example.loginandsignup.R

class UserAdapter (var users: ArrayList<UserModel>) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_model, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int {
        return users.size
    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: UserModel) {
            val ivUserImage = itemView.findViewById<ImageView>(R.id.ivUserImage)
            val tvFirstName = itemView.findViewById<TextView>(R.id.tvFirstName)
            val tvLastName = itemView.findViewById<TextView>(R.id.tvLastName)

            tvFirstName.text = user.FirstName
            tvLastName.text = user.LastName

            Glide.with(itemView).load(user.LinkPhoto).into(ivUserImage)
        }

        init {
            val tvMoreInfo = itemView.findViewById<TextView>(R.id.tvMoreInfo)
            tvMoreInfo.setOnClickListener {
                val pos = adapterPosition
                if (pos != RecyclerView.NO_POSITION) {
                    (itemView.context as ListUserActivity).moreInfo(pos+1)
                }
            }
        }
    }
}