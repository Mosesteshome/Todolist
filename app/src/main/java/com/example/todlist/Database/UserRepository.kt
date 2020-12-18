package com.example.todlist.Database

import androidx.lifecycle.LiveData

class UserRepository(private val userDao:UserDao) {

    val readAllData : LiveData<List<User>> = userDao.readAllData()

    suspend fun adduser(user:User){
        userDao.adduser(user)
    }
    suspend fun updateUser(user: User){
        userDao.updateUser(user)
    }
    suspend fun deleteUser(user:User){
        userDao.deleteUser(user)
    }
    suspend fun deleteAllUsers(){
        userDao.deleteAllUsers()
    }

}