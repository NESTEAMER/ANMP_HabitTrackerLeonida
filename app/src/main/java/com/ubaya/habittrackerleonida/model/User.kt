package com.ubaya.habittrackerleonida.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey
    var username: String,
    @ColumnInfo(name = "password")
    var password: String
)