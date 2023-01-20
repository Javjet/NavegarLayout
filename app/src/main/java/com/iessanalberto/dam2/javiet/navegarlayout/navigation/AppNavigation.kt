package com.iessanalberto.dam2.javiet.navegarlayout.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.iessanalberto.dam2.javiet.navegarlayout.screens.*
@Composable

fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(navController =navController, startDestination =  AppScreens.firstScreen.route){
        composable(route= AppScreens.firstScreen.route){FirstScreen(navController)}
        composable(route= AppScreens.secondScreen.route){SecondScreen(navController )}
    }
}