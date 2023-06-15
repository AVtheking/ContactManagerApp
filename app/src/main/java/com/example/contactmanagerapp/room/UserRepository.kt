package com.example.contactmanagerapp.room

class UserRepository(private val dao: UserDao) {
    val users=dao.getallusers()
    suspend fun insert(user:User):Long{
        return dao.insertUser(user)

    }
    suspend fun update(user: User){
        return dao.updateUser(user)
    }
    suspend fun delete(user:User){
        return dao.deleteUser(user)
    }
    suspend fun deleteall(){
        return dao.deleteAll()
    }
}