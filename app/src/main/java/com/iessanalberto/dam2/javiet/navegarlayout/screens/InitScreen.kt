package com.iessanalberto.dam2.javiet.navegarlayout.screens

import android.graphics.Paint.Style
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.iessanalberto.dam2.javiet.navegarlayout.R
import com.iessanalberto.dam2.javiet.navegarlayout.navigation.AppScreens

@Composable
fun InitScreen(navController: NavController) {
    /*Scaffold(topBar = {
        TopAppBar() {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Atra Premo" ,
                modifier = Modifier.clickable {navController.navigate(route = AppScreens.firstScreen.route)  })
            Spacer(modifier = Modifier.width(8.dp))
        }
    }) {


    }*/

    initBodyContent(navController)

}

@Composable
fun initBodyContent(navController: NavController) {


    Column(
        modifier = Modifier

            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top

    ) {

        Spacer(modifier = Modifier.height(40.dp))
        Box(
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth()
        ) {
            Row(
                Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Top
            ) {
                Image(
                    painter = painterResource(id = R.drawable.onceflogonobackground),
                    contentDescription = "Logo 11F", Modifier.size(110.dp)
                )

                Text(
                    "Quien Soy,\nCientificas",
                    fontSize = 40.sp,
                    color = Color.White,
                    modifier = Modifier.width(250.dp)
                )
                Spacer(modifier = Modifier.width(20.dp))

            }

        }

        Spacer(modifier = Modifier.height(40.dp))
        Button(modifier = Modifier
            .border(2.dp, Color.Black, shape = RoundedCornerShape(90.dp))
            .width(180.dp)
            .height(60.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Gray,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(90.dp),
            onClick = { navController.navigate(route = AppScreens.gameScreen.route) }) {
            Text(text = "Juega", fontSize = 20.sp)
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(modifier = Modifier
            .border(2.dp, Color.Black, shape = RoundedCornerShape(90.dp))
            .width(180.dp)
            .height(60.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Gray,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(90.dp),
            onClick = { navController.navigate(route = AppScreens.firstScreen.route) }) {
            Text(text = "Aprende", fontSize = 20.sp)
        }

        Spacer(modifier = Modifier.height(20.dp))
        Button(modifier = Modifier
            .border(2.dp, Color.Black, shape = RoundedCornerShape(90.dp))
            .width(180.dp)
            .height(60.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Gray,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(90.dp),
            onClick = { navController.navigate(route = AppScreens.secondScreen.route) }) {
            Text(text = "Instrucciones", fontSize = 20.sp)
        }
        Spacer(modifier = Modifier.height(20.dp))
    }


}
