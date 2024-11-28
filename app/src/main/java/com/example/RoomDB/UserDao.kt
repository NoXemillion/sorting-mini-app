package com.example.RoomDB

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface UserDao {

    @Insert
    suspend fun insertNumbers(numbers : List<User>)

    @Query("SELECT * FROM numbers")
    suspend fun getAllUsers(): List<User>

    @Query("DELETE FROM numbers")
    suspend fun clearTable()

}