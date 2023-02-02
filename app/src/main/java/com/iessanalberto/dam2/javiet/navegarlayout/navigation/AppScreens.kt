package com.iessanalberto.dam2.javiet.navegarlayout.navigation

sealed class AppScreens(val route : String){
    object  firstScreen : AppScreens(route ="first_screen")
    object  secondScreen : AppScreens(route ="second_screen")
    object  initScreen : AppScreens(route ="init_screen")
    object  gameScreen : AppScreens(route ="game_screen")
}
