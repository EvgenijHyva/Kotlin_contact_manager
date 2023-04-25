package com.example.contactsmanager.viewModel

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
        if (isUpdateOrDelete) {
            // updates
            userToUpdateOrDelete.name  = inputName.value!!
            userToUpdateOrDelete.email = inputEmail.value!!

            update(userToUpdateOrDelete)

        } else {
            val name = inputName.value!! // !! not null, throw NotNullException()
            val email = inputEmail.value!!

            insert(User(0, name, email))

            inputName.value = null
            inputEmail.value = null
        }
    }

    private fun insert(user: User) = viewModelScope.launch {
        repository.insert(user)
    }

    fun clearAllOrDelete() {
        if(isUpdateOrDelete) {
            delete(userToUpdateOrDelete)
        } else {
            clearAll()
        }
    }

    private fun clearAll() = viewModelScope.launch {
        repository.deleteAll()
    }

    private fun resetButtonsAndFieldsTexts() {
        // reset buttons and fields
        inputName.value = null
        inputEmail.value = null
        isUpdateOrDelete = false

        saveOrUpdateButtonText.value = "Save"
        clearAllOrDeleteButtonText.value = "Clear all"
    }

    private fun update(user: User) = viewModelScope.launch {
        repository.update(user)
        resetButtonsAndFieldsTexts()
    }

    private fun delete(user: User) = viewModelScope.launch {
        repository.delete(user)
        resetButtonsAndFieldsTexts()
    }

    fun initUpdateAndDelete(user: User) {
        inputName.value = user.name
        inputEmail.value = user.email
        isUpdateOrDelete = true
        saveOrUpdateButtonText.value = "Update"
        saveOrUpdateButtonText.value = "Delete"
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

}