package com.iessanalberto.dam2.javiet.navegarlayout.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.iessanalberto.dam2.javiet.navegarlayout.screens.*
@Composable

fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(navController =navController, startDestination =  AppScreens.initScreen.route){
        composable(route= AppScreens.firstScreen.route){LearnScreen(navController)}
        composable(route= AppScreens.gameScreen.route){GameScreen(navController)}
        composable(route= AppScreens.initScreen.route){InitScreen(navController)}
        composable(route= AppScreens.secondScreen.route){SecondScreen(navController )}
    }
}