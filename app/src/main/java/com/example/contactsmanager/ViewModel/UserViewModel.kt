package com.example.contactsmanager.ViewModel

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.contactsmanager.room.User
import com.example.contactsmanager.room.UserRepository

class UserViewModel (private val repository: UserRepository): ViewModel(), Observable { // extends
    val users = repository.users

    private var isUpdateOrDelete = false
    private lateinit var userToUpdateOrDelete: User

    @Bindable
    val inputName: MutableLiveData<String>()



}