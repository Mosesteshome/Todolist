package com.example.todlist.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)// if there is the same user it will ignore it
    suspend fun adduser(User:User)
    @Query("select * FROM user_data ORDER BY id asc")
    fun readAllData():LiveData<List<User>>
}