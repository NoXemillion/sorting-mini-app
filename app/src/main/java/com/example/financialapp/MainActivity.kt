package com.example.financialapp


import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.room.Room
import com.example.Data.DataLoader
import com.example.RoomDB.AppDatabase
import com.example.financialapp.ui.theme.FinancialAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.financialapp.ui.theme.backgroundFirstColor
import com.example.financialapp.ui.theme.backgroundSecondColor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch




class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {



        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java ,
            "app_database"
        ).build()
        Log.d("Database", "Database created successfully: $db")
        var dataLoader = DataLoader(this@MainActivity , db)



        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FinancialAppTheme {

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->


                    Box(modifier = Modifier.fillMaxSize().padding(innerPadding)){
                        Image(
                            painter = painterResource(R.drawable.background),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                        Row(modifier = Modifier.fillMaxWidth().padding(20.dp) ,
                            horizontalArrangement = Arrangement.Center){
                            Text(text = "Package Sorting App" ,
                                modifier = Modifier.fillMaxWidth() , style = TextStyle(
                                    fontFamily = FontFamily(Font(R.font.bungee_spice)) ,
                                    fontSize = 30.sp
                                ))
                        }
                        Column(modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center ,
                            horizontalAlignment = Alignment.CenterHorizontally){

                            Text(text = "Sort" , style = TextStyle(
                                fontFamily = FontFamily(Font(R.font.bungee_spice)) ,
                                fontSize = 70.sp ,
                                fontWeight = FontWeight.Bold
                            ))
                            Spacer(modifier = Modifier.height(30.dp))
                            Button(onClick = {
                                CoroutineScope(Dispatchers.Main).launch {
                                    dataLoader.loadJsonToDatabase()
                                }
                            } , modifier = Modifier.width(150.dp)){
                                Text(text = "Add" , style = TextStyle(
                                    fontSize = 20.sp,
                                    fontFamily = FontFamily(Font(R.font.bungee_spice))
                                )
                                )
                            }
                            Spacer(modifier = Modifier.height(30.dp))
                            Button(onClick = {
                                CoroutineScope(Dispatchers.Main).launch {
                                    dataLoader.radixSorting()
                                }
                            } , modifier = Modifier.width(200.dp)){
                                Text(text = "Radix Sort" , style = TextStyle(
                                    fontSize = 20.sp ,
                                    fontFamily = FontFamily(Font(R.font.bungee_spice))
                                ))
                            }
                            Spacer(modifier = Modifier.height(30.dp))
                            Button(onClick = {
                                CoroutineScope(Dispatchers.Main).launch {
                                    dataLoader.quickSorting()
                                }
                            } , modifier = Modifier.width(200.dp)){
                                Text(text = "Quick Sort" , style = TextStyle(
                                    fontSize = 20.sp,
                                    fontFamily = FontFamily(Font(R.font.bungee_spice)) ,
                                    textAlign = TextAlign.Center
                                ))
                            }

                        }

                    }

                }
            }
        }
    }
}


