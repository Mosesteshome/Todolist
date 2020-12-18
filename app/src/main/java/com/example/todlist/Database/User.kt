package com.example.todlist.Database

import android.os.Parcelable
import android.widget.CheckBox
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName ="user_data")
data class User(
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        val title: String,
        val description: String,
         var statu:Boolean
        //val date:Date
) :Parcelable
{

}