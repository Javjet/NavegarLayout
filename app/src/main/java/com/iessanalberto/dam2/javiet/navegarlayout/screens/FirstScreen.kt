package com.iessanalberto.dam2.javiet.navegarlayout.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.iessanalberto.dam2.javiet.navegarlayout.navigation.AppScreens

@Composable
fun FirstScreen(navController: NavController){
    Scaffold() {
        BodyContent(navController)
    }
}

@Composable
fun BodyContent(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Text(text="Hola mundo Uno")
        Button(onClick = {navController.navigate(route = AppScreens.secondScreen.route)}){
            Text(text="Navega")
        }
    }
}
