package com.example.contactmanagerapp.ViewModel

import android.database.Observable
import androidx.databinding.Bindable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contactmanagerapp.room.User
import com.example.contactmanagerapp.room.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository):ViewModel(),androidx.databinding.Observable {
    val users = repository.users
    private var isUpdateOrDelete = false
    private lateinit var usertoUpdateOrDelete: User

    @Bindable
    val inputName = MutableLiveData<String? >()


    @Bindable
    val inputNumber = MutableLiveData<String? >()

    @Bindable
    val SaveOrUpdateButtonText = MutableLiveData<String>()

    @Bindable
    val ClearallOrDeleteButtonText = MutableLiveData<String>()

    init{
        SaveOrUpdateButtonText.value="Add"
        ClearallOrDeleteButtonText.value="Delete"
    }
    fun SaveOrUpdate(){
        val name=inputName.value!!
        val number=inputNumber.value!!
        insert(User(0,name,number))
       inputName.value=null
       inputNumber.value=null
    }
    fun clearAllorDelete()
    {
        clearAll()
    }
    fun insert(user:User)=viewModelScope.launch {
        repository.insert(user)
    }
   fun clearAll()=viewModelScope.launch {
       repository.deleteall()
   }
    fun update(user:User)=viewModelScope.launch {
        repository.update(user)
    }
    fun delele(user:User)=viewModelScope.launch {
        repository.delete(user)
    }

    override fun addOnPropertyChangedCallback(callback: androidx.databinding.Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: androidx.databinding.Observable.OnPropertyChangedCallback?) {
    }
}

