package com.example.app_description_apiary.ui.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.app_description_apiary.R
import com.example.app_description_apiary.data.User

class UserAdapter(private val user: List<User>) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_details, parent, false)
        return UserViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bindView(user[position])
    }

    override fun getItemCount() = user.count()

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image = itemView.findViewById<TextView>(R.id.iv_person_detail)
        val name = itemView.findViewById<TextView>(R.id.tv_name_detail)
        val job = itemView.findViewById<TextView>(R.id.tv_job_detail)

        fun bindView(user: User) {
            image.text = user.urlImage
            name.text = user.name
            job.text = user.job

        }
    }
}