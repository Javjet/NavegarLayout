package com.iessanalberto.dam2.javiet.navegarlayout.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.iessanalberto.dam2.javiet.navegarlayout.navigation.AppScreens
import com.iessanalberto.dam2.javiet.navegarlayout.ui.theme.NavegarLayoutTheme

@Composable
fun FirstScreen(navController: NavController){
    Scaffold() {
        BodyContent(navController)
    }
}

@Composable
fun BodyContent(navController: NavController) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center)
        {
            items(dias){
                    dia -> Text(
                text = dia,
                modifier = Modifier
                    .padding(10.dp),
                fontStyle = FontStyle.Italic,
                fontSize = 60.sp
            )
            Card(

            ) {
                Image(painter = painterResource(id = R.drawable.cervantes_miguel_1),contentDescription = null)
                Text(text = "Mijuel")
                Text(text = "Servantes Zabedra")
            }
    }
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val navController = rememberNavController()
    BodyContent(navController)
}

