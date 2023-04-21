package com.example.contactsmanager.room

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "user")
data class User (
    @ColumnInfo(name = "user_id")
    val id: Int,
    @ColumnInfo(name = "user_name")
    val name: String,
    @ColumnInfo(name = "user_email")
    val email: String
)
