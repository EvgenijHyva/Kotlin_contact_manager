package com.example.contactsmanager.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1)
public abstract class UserDatabase : RoomDatabase() {
    abstract val userDAO: UserDAO

    // singleton
    companion object {
        @Volatile // make field visible for threads
        private var INSTANCE: UserDatabase ?= null
        fun getInstance(context: Context): UserDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    // creating db object
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        UserDatabase::class.java,
                        "users_db"
                    ).build()
                }
                return instance
            }
        }
    }
}