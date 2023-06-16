package com.example.contactmanagerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactmanagerapp.ViewModel.UserViewModel
import com.example.contactmanagerapp.ViewModel.ViewModelFactory
import com.example.contactmanagerapp.ViewUi.MyRecyclerviewAdapter
import com.example.contactmanagerapp.databinding.ActivityMainBinding
import com.example.contactmanagerapp.room.User
import com.example.contactmanagerapp.room.UserDatabase
import com.example.contactmanagerapp.room.UserRepository

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var userViewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)
        val dao=UserDatabase.getInstance(application).userDao
        val repository=UserRepository(dao)
        val factory=ViewModelFactory(repository)

        userViewModel=ViewModelProvider(this,factory).
        get(UserViewModel::class.java)
//        binding.userViewModel=userViewModel
        binding.lifecycleOwner=this
        initRecyclerview()
    }

    private fun initRecyclerview() {
        binding.recyclerview.layoutManager=LinearLayoutManager(this)
        DisplayUsersList()
    }

    private fun DisplayUsersList() {
        userViewModel.users.observe(this, Observer {
            binding.recyclerview.adapter=MyRecyclerviewAdapter(this,it,
                {selectedItem: User -> listItemClicked(selectedItem)},userViewModel)
        })
    }

    private fun listItemClicked(selectedItem: User) {
        userViewModel.initUpdateOrDelete(selectedItem)

    }
}