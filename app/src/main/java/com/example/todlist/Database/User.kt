package com.example.todlist.Database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName ="user_data")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String ,
    val Desc: String)// desc is for Description
{

}