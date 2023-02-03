package com.iessanalberto.dam2.javiet.navegarlayout.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.iessanalberto.dam2.javiet.navegarlayout.CientificasLista
import com.iessanalberto.dam2.javiet.navegarlayout.data.Cientificas
import com.iessanalberto.dam2.javiet.navegarlayout.ui.GameViewModel
import com.iessanalberto.dam2.javiet.navegarlayout.ui.p.OnceColor

@Composable
fun FirstScreen(navController: NavController,gameViewModel: GameViewModel = viewModel()){
    Scaffold() {
        BodyContent(navController)
    }
}


@Composable
fun BodyContent(navController: NavController,gameViewModel: GameViewModel = viewModel()) {

    val gameUiState by gameViewModel.uiState.collectAsState()

    var lista: List<Cientificas> = listOf(CientificasLista[gameUiState.positionLearn])
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp).background(OnceColor),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center)
        {
            items(lista){
                    cientifica ->
                    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                        Spacer(modifier = Modifier.height(20.dp))
                            Image( painter = painterResource(id = cientifica.ImageId),contentDescription = null, modifier = Modifier
                                .size(200.dp)
                                .clip(RoundedCornerShape(55.dp)), alignment = Alignment.Center,)
                            var pistasTotales=""
                            for (i in 0 until cientifica.Pistas.size){
                                pistasTotales+= cientifica.Pistas[i]
                            }
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(text = lista[0].nombre)
                        Spacer(modifier = Modifier.height(10.dp))
                        Box(modifier = Modifier
                            .border(2.dp, Color.Black)
                            .padding(8.dp)){
                            Text(
                                text = pistasTotales,
                                modifier = Modifier
                                    .padding(10.dp),
                                fontStyle = FontStyle.Italic,
                                fontSize = 15.sp
                            )
                        }
                    }
                Row() {
                    IconButton(onClick = {gameViewModel.goBackKey(lista) }) {
                        Icon(
                            Icons.Filled.KeyboardArrowLeft,
                            contentDescription = "Retroceder",
                            tint = Color.LightGray
                        )
                    }

                    IconButton(onClick = {gameViewModel.goForwardKey(lista) }) {
                        Icon(
                            Icons.Filled.KeyboardArrowRight,
                            contentDescription = "Avanzar",
                            tint = Color.LightGray
                        )
                    }
                }


            }
        }
    }

/*@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val navController = rememberNavController()
    BodyContent(navController)
}*/

