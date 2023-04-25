package com.example.contactsmanager.viewUI

import androidx.recyclerview.widget.RecyclerView
import com.example.contactsmanager.databinding.CardItemBinding
import com.example.contactsmanager.room.User

class ListItemViewHolder(private val binding: CardItemBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(user: User, clickListener: (User) -> Unit) {
        binding.nameTextView.text = user.name
        binding.emailTextView.text = user.email

        binding.listItemLayout.setOnClickListener {
            clickListener(user)
        }
    }
}