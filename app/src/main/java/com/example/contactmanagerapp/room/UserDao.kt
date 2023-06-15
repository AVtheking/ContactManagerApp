package com.example.contactmanagerapp.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {

    @Insert
    suspend fun insertUser(user:User):Long

    @Update
    suspend fun updateUser(user:User)

    @Delete
    suspend fun deleteUser(user:User)
    @Query("DELETE FROM user")
    suspend fun deleteAll()

    @Query("select*from user")
    fun getallusers():LiveData<List<User>>
}