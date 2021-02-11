package com.example.app_description_apiary.ui.adapter


import android.telecom.Call
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.app_description_apiary.R
import com.example.app_description_apiary.data.DetailsUser
import com.example.app_description_apiary.databinding.ItemDetailsBinding
import com.squareup.picasso.Picasso


class UserAdapter(private val user: List<DetailsUser>,private val onClick: (DetailsUser) -> Unit) :
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

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemDetailsBinding.bind(itemView)

        fun bindView(user: DetailsUser) {
            Picasso.Builder(itemView.context)
                .build()
                .load(user.urlImage)
                .into(binding.ivPersonDetail)
            binding.tvNameDetail.text = user.name
            binding.tvJobDetail.text = user.job
            binding.floatingButton.setOnClickListener {
                onClick.invoke(user)
            }

        }
    }
}