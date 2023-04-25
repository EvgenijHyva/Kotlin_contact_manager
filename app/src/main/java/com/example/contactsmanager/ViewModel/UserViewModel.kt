package com.example.contactsmanager.ViewModel

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contactsmanager.room.User
import com.example.contactsmanager.room.UserRepository
import kotlinx.coroutines.launch

class UserViewModel (private val repository: UserRepository): ViewModel(), Observable { // extends
    val users = repository.users

    private var isUpdateOrDelete = false
    private lateinit var userToUpdateOrDelete: User

    @Bindable
    val inputName = MutableLiveData<String?>()
    @Bindable
    val inputEmail = MutableLiveData<String?>()
    @Bindable
    val saveOrUpdateButtonText = MutableLiveData<String>()
    @Bindable
    val clearAllOrDeleteButtonText = MutableLiveData<String>()

    init {
        saveOrUpdateButtonText.value = "Save"
        clearAllOrDeleteButtonText.value = "Clear all"
    }

    fun saveOrUpdate() {
        val name = inputName.value!! // !! not null, throw NotNullException()
        val email = inputEmail.value!!

        insert(User(0, name, email))

        inputName.value = null
        inputEmail.value = null
    }

    private fun insert(user: User) = viewModelScope.launch {
        repository.insert(user)
    }

    fun clearAllOrDelete() {
        clearAll()
    }

    private fun clearAll() = viewModelScope.launch {
        repository.deleteAll()
    }

    fun update(user: User) = viewModelScope.launch {
        repository.update(user)
    }

    fun delete(user: User) = viewModelScope.launch {
        repository.delete(user)
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

}