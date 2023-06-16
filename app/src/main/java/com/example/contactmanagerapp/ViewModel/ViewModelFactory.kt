package com.example.contactmanagerapp.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.contactmanagerapp.room.UserRepository
import java.lang.IllegalArgumentException

class ViewModelFactory(private val repository: UserRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(UserViewModel::class.java))
        {
            return UserViewModel(repository) as T
        }
        else
        {
            throw IllegalArgumentException("Unknown view model class")
        }
    }
}