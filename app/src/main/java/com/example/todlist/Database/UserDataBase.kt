package com.example.todlist.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.todlist.DateTypeConverter


@Database(entities = arrayOf(User::class), version = 1, exportSchema = false)
@TypeConverters(DateTypeConverter::class)
abstract class UserDataBase : RoomDatabase(){
    abstract fun userDao(): UserDao

    companion object{
      @Volatile
      private var INSTANCE: UserDataBase? = null

      fun getDatabase(context:Context): UserDataBase{
            val tempInstance = INSTANCE
          if (tempInstance != null){
              return tempInstance
          }
          synchronized(this ){
              val instance = Room.databaseBuilder(
                  context.applicationContext,
                  UserDataBase::class.java,
                  "user_database"
              ).build()
              INSTANCE = instance
              return instance
          }
      }
    }


}