package com.example.todlist.Database

import androidx.lifecycle.LiveData
import androidx.room.*
import java.util.*

@Dao
interface UserDao {


    @Insert(onConflict = OnConflictStrategy.IGNORE)// if there is the same user it will ignore it
    suspend fun adduser(User:User)

    @Update
    suspend fun updateUser(user: User)

    @Query("select * FROM user_data ORDER BY id asc")
    fun readAllData():LiveData<List<User>>

    @Delete
    suspend fun deleteUser(user:User)

    @Query("DELETE FROM user_data")
    suspend fun deleteAllUsers()

    @Query("SELECT * FROM user_data WHERE date BETWEEN :from AND :to")
    fun findUsersBornBetweenDates(from: Date, to: Date): List<User>
}

