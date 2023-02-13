/*
 * Copyright (c)2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.iessanalberto.dam2.javiet.navegarlayout.screens

import android.app.Activity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.iessanalberto.dam2.javiet.navegarlayout.R
import com.iessanalberto.dam2.javiet.navegarlayout.data.Cientificas
import com.iessanalberto.dam2.javiet.navegarlayout.data.Cientificas_Act_Max
import com.iessanalberto.dam2.javiet.navegarlayout.specificControls.ExposedDropdownMenu
import com.iessanalberto.dam2.javiet.navegarlayout.specificControls.rememberExposedMenuStateHolder
import com.iessanalberto.dam2.javiet.navegarlayout.ui.GameViewModel
import com.iessanalberto.dam2.javiet.navegarlayout.ui.theme.OnceColor

/*
import com.example.android.unscramble.R
import com.example.android.unscramble.ui.theme.UnscrambleTheme
*/


@Composable
fun GameScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    gameViewModel: GameViewModel = viewModel()
) {

    val gameUiState by gameViewModel.uiState.collectAsState()




    Column(
        modifier = modifier
            .fillMaxSize(1.0f),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopAppBar(

            modifier = Modifier.fillMaxWidth(),
        ) {
            GameStatus(
                scientificCount = gameUiState.currentScientific,
                score = gameUiState.score
            )
        }
        Image(
            painter = painterResource(id = R.drawable.unknown_girl), contentDescription = null,
            modifier = Modifier.size(100.dp), alignment = Alignment.Center,
        )

        gameUiState.currentScientificData?.let {
            Text(
                text = it.nombre,
                fontSize = 30.sp, fontStyle = FontStyle.Italic, fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
        Box(
            modifier = Modifier
                .fillMaxHeight(0.55f)
        ) {
            GameLayout(
                userGuess = gameViewModel.userGuess,
                isGuessWrong = gameUiState.isGuessedScientificWrong,
                onUserGuessChanged = { gameViewModel.updateUserGuess(it) },
                onKeyboardDone = { gameViewModel.checkUserGuess() },
                gameViewModel = gameViewModel,
                currentScientific = gameUiState.currentScientificData,
            )
        }
        Box(
            modifier = Modifier
                .padding(16.dp, 0.dp, 16.dp, 16.dp),
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val stateHolder = rememberExposedMenuStateHolder()
                OutlinedTextField(
                    //value = userGuess,
                    value = gameViewModel.userGuess,
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .onGloballyPositioned {
                            stateHolder.onSize(it.size.toSize())
                        },
                    /*.clickable {expanded = true },*/
                    onValueChange = { gameViewModel.updateUserGuess(it) },

                    label = {
                        if (gameUiState.isGuessedScientificWrong) {
                            Text(stringResource(R.string.wrong_guess))
                        } else {
                            Text(stringResource(R.string.enter_your_word))
                        }
                    },
                    trailingIcon = {
                        Icon(painter = painterResource(id = stateHolder.icon),
                            contentDescription = null,
                            Modifier.clickable { stateHolder.onEnabled(!(stateHolder.enabled)) }
                        )
                    },
                    isError = gameUiState.isGuessedScientificWrong,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = { gameViewModel.checkUserGuess() }
                    ),

                    )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    OutlinedButton(
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.White,
                            contentColor = Color.Gray
                        ),
                        modifier = Modifier
                            .weight(1f)
                            .padding(8.dp, 0.dp, end = 8.dp, 0.dp)
                            .fillMaxWidth()
                            .height(55.dp)
                            .border(2.dp, Color.Black, shape = RoundedCornerShape(90.dp)),

                        shape = RoundedCornerShape(90.dp),
                        onClick = { gameViewModel.skipScientific() },
                    ) {
                        Text(
                            stringResource(R.string.skip),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Button(
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.Gray,
                            contentColor = Color.White
                        ),
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 8.dp, 0.dp, 0.dp, 0.dp)
                            .fillMaxWidth()
                            .height(55.dp)
                            .border(2.dp, Color.Black, shape = RoundedCornerShape(90.dp)),

                        shape = RoundedCornerShape(90.dp),
                        onClick = { gameViewModel.checkUserGuess() }

                    ) {
                        Text(
                            stringResource(R.string.submit),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                ExposedDropdownMenu(stateHolder = stateHolder, gameViewModel)
            }
        }

        BottomAppBar(modifier = Modifier.fillMaxWidth()) {

        }
        if (gameUiState.isGameOver) {
            FinalScoreDialog(
                score = gameUiState.score,
                onPlayAgain = { gameViewModel.resetGame() }
            )
        }
    }
}


@Composable
fun GameStatus(scientificCount: Int, score: Int, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
            .size(48.dp),
    ) {
        Text(
            text = stringResource(R.string.scientific_count) + ": $scientificCount/$Cientificas_Act_Max",
            fontSize = 18.sp,
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.End),
            text = stringResource(R.string.score, score) + ": $score",
            fontSize = 18.sp,
        )
    }
}

@Composable
fun GameLayout(
    currentScientific: Cientificas?,
    isGuessWrong: Boolean,
    userGuess: String,
    onUserGuessChanged: (String) -> Unit,
    onKeyboardDone: () -> Unit,
    gameViewModel: GameViewModel,
    modifier: Modifier = Modifier
) {
    var cientifica: List<Cientificas> = listOf(currentScientific) as List<Cientificas>
    var imageId: Int = 0

    // state of the menu
    var expanded by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val gameUiState by gameViewModel.uiState.collectAsState()

        if (!gameUiState.CompressView) {
            LazyColumn(
                modifier = Modifier

                    .padding(0.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            )
            {
                items(cientifica) { cientifica ->

                    var pistasTotales = ""
                    if (gameUiState.cluePosition <= cientifica.Pistas.size) {
                        for (i in 0 until gameUiState.cluePosition) {

                            if (!cientifica.Pistas[i].contains("ImagenExterna")) {
                                pistasTotales += cientifica.Pistas[i] + "\n"
                            } else {
                                pistasTotales += cientifica.Pistas[i].split("ImagenExterna")[0]
                                imageId = cientifica.Pistas[i].split("ImagenExterna")[1].toInt()

                            }
                        }
                    }

                    Box(
                        modifier = Modifier

                            .fillMaxHeight()
                            .fillMaxWidth(0.8f)
                            .border(2.dp, Color.Black, RoundedCornerShape(55.dp))
                            .clip(RoundedCornerShape(55.dp))
                            .background(Color.White)
                            .padding(15.dp)
                            .align(Alignment.CenterHorizontally),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = pistasTotales,
                                fontStyle = FontStyle.Italic,
                                fontSize = 16.sp
                            )
                            if (imageId != 0) {
                                Image(
                                    painter = painterResource(id = imageId),
                                    contentDescription = null,
                                    alignment = Alignment.BottomCenter,
                                )
                            }
                            var visible = true
                            if (gameUiState.cluePosition >= 10) {
                                visible = false
                            }
                            this@Column.AnimatedVisibility(visible = visible) {
                                Button(modifier = Modifier
                                    .border(
                                        2.dp,
                                        Color.Black,
                                        shape = RoundedCornerShape(90.dp)
                                    )
                                    .width(180.dp)
                                    .height(60.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        backgroundColor = Color.Gray,
                                        contentColor = Color.White
                                    ),
                                    shape = RoundedCornerShape(90.dp),
                                    onClick = { gameViewModel.updateClue() }) {
                                    Text(
                                        text = "Siguiente Pista",
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Icon(
                                        Icons.Filled.KeyboardArrowRight,
                                        contentDescription = "Avanzar",
                                        tint = Color.LightGray
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(20.dp))
                            IconButton(modifier = Modifier
                                .background(Color.Gray, shape = RoundedCornerShape(100.dp))
                                .border(
                                    2.dp,
                                    Color.Black,
                                    shape = RoundedCornerShape(100.dp)
                                ),
                                onClick = { gameViewModel.compress() }) {
                                Icon(
                                    Icons.Filled.KeyboardArrowUp,
                                    contentDescription = "Comprimir",
                                    tint = Color.LightGray
                                )
                            }
                        }
                    }


                }
            }

        } else {
            IconButton(modifier = Modifier
                .background(Color.Gray, shape = RoundedCornerShape(100.dp))
                .border(
                    2.dp,
                    Color.Black,
                    shape = RoundedCornerShape(100.dp)
                ),
                onClick = { gameViewModel.extends() }) {
                Icon(
                    Icons.Filled.KeyboardArrowDown,
                    contentDescription = "Descomprimir",
                    tint = Color.LightGray
                )
            }
        }


        /*val stateHolder = rememberExposedMenuStateHolder()
        OutlinedTextField(
            //value = userGuess,
            value = userGuess,
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned {
                    stateHolder.onSize(it.size.toSize())
                },
            /*.clickable {expanded = true },*/
            onValueChange = onUserGuessChanged,

            label = {
                if (isGuessWrong) {
                    Text(stringResource(R.string.wrong_guess))
                } else {
                    Text(stringResource(R.string.enter_your_word))
                }
            },
            trailingIcon = {
                Icon(painter = painterResource(id = stateHolder.icon),
                    contentDescription = null,
                    Modifier.clickable { stateHolder.onEnabled(!(stateHolder.enabled)) }
                )
            },
            isError = isGuessWrong,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { onKeyboardDone() }
            ),

            )


        ExposedDropdownMenu(stateHolder = stateHolder, gameViewModel)*/


    }
}


/*
 * Creates and shows an AlertDialog with final score.
 */
@Composable
private fun FinalScoreDialog(
    score: Int,
    onPlayAgain: () -> Unit,
    modifier: Modifier = Modifier
) {
    val activity = (LocalContext.current as Activity)

    AlertDialog(
        onDismissRequest = {
            // Dismiss the dialog when the user clicks outside the dialog or on the back
            // button. If you want to disable that functionality, simply use an empty
            // onCloseRequest.
        },
        title = { Text(stringResource(R.string.congratulations)) },
        text = { Text(stringResource(R.string.you_scored, score)) },
        modifier = modifier,
        dismissButton = {
            TextButton(
                onClick = {
                    activity.finish()
                }
            ) {
                Text(text = stringResource(R.string.exit))
            }
        },
        confirmButton = {
            TextButton(
                onClick = onPlayAgain
            ) {
                Text(text = stringResource(R.string.play_again))
            }
        }
    )
}


/*@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    UnscrambleTheme {
        GameScreen()
    }
}*/