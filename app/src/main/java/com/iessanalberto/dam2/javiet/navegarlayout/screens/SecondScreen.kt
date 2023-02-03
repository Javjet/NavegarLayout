package com.iessanalberto.dam2.javiet.navegarlayout.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.iessanalberto.dam2.javiet.navegarlayout.CientificasLista
import com.iessanalberto.dam2.javiet.navegarlayout.navigation.AppScreens

@Composable
fun SecondScreen(navController: NavController){
    Scaffold(topBar = {
        TopAppBar() {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Atra Premo" ,
            modifier = Modifier.clickable {navController.navigate(route = AppScreens.firstScreen.route)  })
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Segunda Pantalla")
        }
    }) {

        secondBodyContent(navController)
    }
}

@Composable
fun secondBodyContent(navController: NavController) {
    Column(Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){
        Text(text="Hola mundo Due")
    }
}

