package com.example.financialapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.Data.DataLoader
import com.example.financialapp.ui.theme.backgroundFirstColor
import com.example.financialapp.ui.theme.backgroundSecondColor


@Composable
fun RadixSortScreen(navController: NavController , viewModel : DataLoader) {


    Column(modifier = Modifier.fillMaxSize().background(
        brush = Brush.verticalGradient(
            colors = listOf(backgroundFirstColor , backgroundSecondColor)
        )
    ) ,
        horizontalAlignment = Alignment.CenterHorizontally){

        Button(onClick = {
            navController.navigate("main")
        }){
            Text("Main")
        }
        Spacer(modifier = Modifier.height(10.dp))
        LazyColumn {
            items(viewModel.radixSortList.size) { item ->
                Text(text = "${viewModel.radixSortList[item]}")
                Spacer(modifier = Modifier.height(10.dp))
            }

        }
    }



}