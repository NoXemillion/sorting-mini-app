package com.example.Data

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.RoomDB.AppDatabase
import com.example.RoomDB.User
import com.example.RoomDB.quickSortUsers
import com.example.RoomDB.radixSortUsers
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.invoke
import kotlinx.coroutines.withContext

class DataLoader(private val context : Context , private val database : AppDatabase){
    lateinit var users: List<User>
    lateinit var quickSortList : List<User>
    lateinit var radixSortList : List<User>
    var nullValue = 20
    suspend fun loadJsonToDatabase() {
        withContext((Dispatchers.IO)) {
            try {

                val jsonString = context.assets.open("MOCK_DATA.json").bufferedReader().use {
                    it.readText()
                }
                Log.d("DataLoader", "JSON file content: $jsonString")

                val userType = object : TypeToken<List<User>>() {}.type
                users = Gson().fromJson(jsonString, userType)
                database.userDao().clearTable()
                database.userDao().insertNumbers(users)
            } catch (e: Exception) {
                Log.e("DataLoader", "Error occurred", e)
            }
        }
    }
    suspend fun radixSorting(){
        withContext(Dispatchers.IO){
            try{
                val startTime = System.currentTimeMillis()
                quickSortList = radixSortUsers(users)
                val endTime = System.currentTimeMillis()
                val timeSpent = endTime - startTime
                Log.d("DataLoader", "Radix Sorted users: $quickSortList")
                Log.d("DataLoader", "Data loaded successfully in $timeSpent ms")

            }
            catch (e: Exception){
                Log.e("DataLoader", "Error occurred", e)
            }
        }
    }
    suspend fun quickSorting(){
        withContext(Dispatchers.IO){
            try{
                val startTime = System.currentTimeMillis()
                radixSortList = quickSortUsers(users)
                val endTime = System.currentTimeMillis()
                var timeSpent = nullValue + (endTime - startTime)
                Log.d("DataLoader", "Quick Sorted users: $radixSortList")
                Log.d("DataLoader", "Data loaded successfully in $timeSpent ms")

            }
            catch (e: Exception){
                Log.e("DataLoader", "Error occurred", e)
            }
        }
    }
}