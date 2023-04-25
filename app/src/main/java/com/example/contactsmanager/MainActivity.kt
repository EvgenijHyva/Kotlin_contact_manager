package com.example.contactsmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Display
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactsmanager.ViewModel.UserViewModel
import com.example.contactsmanager.ViewModel.UserViewModelFactory
import com.example.contactsmanager.databinding.ActivityMainBinding
import com.example.contactsmanager.room.User
import com.example.contactsmanager.room.UserDatabase
import com.example.contactsmanager.room.UserRepository
import com.example.contactsmanager.viewUI.RecyclerViewAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private  lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // Room DB
        val dao = UserDatabase.getInstance(application).userDAO
        val repository = UserRepository(dao)
        val factory = UserViewModelFactory(repository)

        // connecting
        userViewModel = ViewModelProvider(this, factory).get(UserViewModel::class.java)

        binding.userViewModel = userViewModel

        binding.lifecycleOwner = this

        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.recycleView.layoutManager = LinearLayoutManager(this)
        displayUsersList()
    }

    private fun displayUsersList() {
        userViewModel.users.observe(this, Observer {
            binding.recycleView.adapter = RecyclerViewAdapter(
                it,
                { selectedItem: User -> listItemClicked(selectedItem) } // lambda
            )
        })
    }

    private fun listItemClicked(selectedItem: User) {
        Toast.makeText(
            this, "selected name is ${selectedItem.name}", Toast.LENGTH_LONG
        ).show()

        userViewModel.initUpdateAndDelete(selectedItem)
    }


}