
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
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.iessanalberto.dam2.javiet.navegarlayout.R
import com.iessanalberto.dam2.javiet.navegarlayout.data.Cientificas
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
            .background(OnceColor)
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .padding(16.dp,0.dp,16.dp,16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        GameStatus(
            scientificCount = gameUiState.currentScientific,
            score = gameUiState.score
        )
        GameLayout(
            userGuess = gameViewModel.userGuess,
            isGuessWrong = gameUiState.isGuessedScientificWrong,
            onUserGuessChanged = { gameViewModel.updateUserGuess(it) },
            onKeyboardDone = {gameViewModel.checkUserGuess() },
            gameViewModel= gameViewModel,
            currentScientific = gameUiState.currentScientificData,
        )
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            OutlinedButton(
                onClick = { gameViewModel.skipScientific() },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            ) {
                Text(stringResource(R.string.skip))
            }

            Button(
                modifier = modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(start = 8.dp),
                onClick = { gameViewModel.checkUserGuess() }
            ) {
                Text(stringResource(R.string.submit))
            }
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
fun GameStatus(scientificCount: Int, score: Int,modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .size(48.dp),
    ) {
        Text(
            text = stringResource(R.string.scientific_count, scientificCount),
            fontSize = 18.sp,
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.End),
            text = stringResource(R.string.score, score),
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
    modifier: Modifier = Modifier) {
    var cientifica: List<Cientificas> = listOf(currentScientific) as List<Cientificas>
    var imageId: Int =0

    Text(text = "asdasd")
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
        val gameUiState by gameViewModel.uiState.collectAsState()
        Image( painter = painterResource(id = R.drawable.unknown_girl),contentDescription = null,
            modifier = Modifier.size(100.dp),alignment = Alignment.Center, )

        Text(
            //text = stringResource(R.string.whoim),
            text = cientifica[0].nombre,
            fontSize = 30.sp, fontStyle = FontStyle.Italic, fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        LazyColumn(
            modifier = Modifier
                .padding(20.dp)
                .heightIn(0.dp, 1000.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        )
        {
            items(cientifica){
                    cientifica ->
                var pistasTotales=""
                if(gameUiState.cluePosition<=cientifica.Pistas.size){
                for (i in 0 until gameUiState.cluePosition){

                    if (!cientifica.Pistas[i].contains("ImagenExterna")){
                        pistasTotales+= cientifica.Pistas[i]+"\n"
                    }else {
                        pistasTotales+=cientifica.Pistas[i].split("ImagenExterna")[0]
                        imageId= cientifica.Pistas[i].split("ImagenExterna")[1].toInt()

                    }
                }
                }

                Box(modifier = Modifier
                    .border(2.dp, Color.Black, RoundedCornerShape(55.dp))
                    .clip(RoundedCornerShape(55.dp))
                    .background(Color.White)
                    .padding(20.dp)
                ){
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = pistasTotales,
                            modifier = Modifier
                                .padding(10.dp),
                            fontStyle = FontStyle.Italic,
                            fontSize = 16.sp
                        )
                        if (imageId!=0) {
                                Image(
                                    painter = painterResource(id = imageId),
                                    contentDescription = null,
                                    alignment = Alignment.BottomCenter,
                                )
                        }
                        var visible=true
                        if (gameUiState.cluePosition>=10){
                            visible=false
                        }
                        this@Column.AnimatedVisibility(visible = visible) {
                            Button(modifier = Modifier
                                .border(2.dp, Color.Black, shape = RoundedCornerShape(90.dp))
                                .width(180.dp)
                                .height(60.dp),
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray,contentColor = Color.White),
                                shape=RoundedCornerShape(90.dp),
                                onClick = {gameViewModel.updateClue() }) {
                                Text(text = "Siguiente Pista")
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
        }
        OutlinedTextField(
            value = userGuess,
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = onUserGuessChanged,

            label = {
                if (isGuessWrong) {
                    Text(stringResource(R.string.wrong_guess))
                } else {
                    Text(stringResource(R.string.enter_your_word))
                }
            },
            isError = isGuessWrong,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { onKeyboardDone() }
            ),
        )

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
        text = { Text(stringResource(R.string.you_scored, score))},
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