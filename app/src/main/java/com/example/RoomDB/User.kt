package com.example.RoomDB

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "numbers")
data class User(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    val id : Int = 0,
    @SerializedName("numbers")
    val number : Int = 0
)

