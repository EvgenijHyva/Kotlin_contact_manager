package com.example.contactsmanager.viewUI

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.contactsmanager.R
import com.example.contactsmanager.databinding.CardItemBinding
import com.example.contactsmanager.room.User

class RecyclerViewAdapter(
    private val usersList: List<User>,
    private val clickListener: (User) -> Unit // lambda
    ): RecyclerView.Adapter<ListItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: CardItemBinding = DataBindingUtil.inflate(
            layoutInflater, R.layout.card_item, parent, false
        )
        return ListItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return usersList.size
    }

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        holder.bind(usersList[position], clickListener)
    }

}

