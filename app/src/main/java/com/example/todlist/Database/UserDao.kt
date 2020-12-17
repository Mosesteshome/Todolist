package com.example.todlist.Database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {


    @Insert(onConflict = OnConflictStrategy.IGNORE)// if there is the same user it will ignore it
    suspend fun adduser(User:User)

    @Update
    suspend fun updateUser(user: User)

    @Query("select * FROM user_data ORDER BY id asc")
    fun readAllData():LiveData<List<User>>
}