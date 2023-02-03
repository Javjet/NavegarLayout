package com.iessanalberto.dam2.javiet.navegarlayout.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.flow.MutableStateFlow
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import com.iessanalberto.dam2.javiet.navegarlayout.CientificasLista
import com.iessanalberto.dam2.javiet.navegarlayout.data.Cientificas
import com.iessanalberto.dam2.javiet.navegarlayout.data.Cientificas_Act
import com.iessanalberto.dam2.javiet.navegarlayout.data.SCORE_INCREASE

class GameViewModel : ViewModel() {

    // Game UI state
    private val _uiState = MutableStateFlow(GameUiState())
    val uiState : StateFlow<GameUiState> = _uiState.asStateFlow()
    // Declare private mutable variable that can only be modified
    // within the class it is declared.
    private var _count = 0

    // Declare another public immutable field and override its getter method.
    // Return the private property's value in the getter method.
    // When count is accessed, the get() function is called and
    // the value of _count is returned.
    val count: Int
        get() = _count
    private lateinit var currentScientific: Cientificas
    private var usedScientifics: MutableSet<Cientificas> = mutableSetOf()
    var userGuess by mutableStateOf("")
        private set
    init {
        resetGame()
    }

    private fun pickRandomCientificas(): Cientificas {
        // Continue picking up a new random word until you get one that hasn't been used before
        currentScientific = CientificasLista.random()
        if (usedScientifics.contains(currentScientific)) {
            return pickRandomCientificas()
        } else {
            usedScientifics.add(currentScientific)
            return currentScientific;
        }
    }
    private fun updateGameState(updatedScore: Int) {
        if (usedScientifics.size == Cientificas_Act){
        //Last round in the game, update isGameOver to true, don't pick a new word
            _uiState.update { currentState ->
                currentState.copy(
                    isGuessedScientificWrong = false,
                    score = updatedScore,
                    currentScientific = currentState.currentScientific.inc(),
                    isGameOver = true
                )
            }        } else{
            // Normal round in the game
            _uiState.update { currentState ->
                currentState.copy(
                    isGuessedScientificWrong = false,
                    currentScientific = currentState.currentScientific.inc(),
                    currentScientificData = pickRandomCientificas(),
                    score = updatedScore
                )
            }
        }
    }

    fun checkUserGuess() {
        if (userGuess.equals(currentScientific.nombre, ignoreCase = true)) {

            // User's guess is correct, increase the score
            // and call updateGameState() to prepare the game for next round
            val updatedScore = _uiState.value.score.plus(SCORE_INCREASE)
            updateGameState(updatedScore)
        } else {
            // User's guess is wrong, show an error
            _uiState.update { currentState ->
                currentState.copy(isGuessedScientificWrong = true)
            }
        }
        // Reset user guess
        updateUserGuess("")
    }

    private fun shuffleCurrentWord(word: String): String {
        val tempWord = word.toCharArray()
        // Scramble the word
        do{
            tempWord.shuffle()
        }while (String(tempWord).equals(word))
        return String(tempWord)
    }

    fun resetGame() {
        usedScientifics.clear()
        _uiState.value = GameUiState(currentScientificData = pickRandomCientificas())
    }
    fun updateUserGuess(guessedWord: String){
        userGuess = guessedWord
    }

    fun goForwardKey(lista: List<Cientificas>) {
        if (!CientificasLista[CientificasLista.indexOf(lista[0])].equals((CientificasLista.last()))){
            _uiState.update { currentState ->
                currentState.copy(positionLearn = currentState.positionLearn+1)
            }
        }
        else{
            _uiState.update { currentState ->
                currentState.copy(positionLearn = 0)
            }
        }
    }
    fun goBackKey(lista: List<Cientificas>) {
        if (!CientificasLista[CientificasLista.indexOf(lista[0])].equals((CientificasLista.first()))){
            _uiState.update { currentState ->
                currentState.copy(positionLearn = currentState.positionLearn-1)
            }
        }
        else{
            _uiState.update { currentState ->
                currentState.copy(positionLearn = CientificasLista.size-1)
            }
        }
    }

    fun skipWord() {
        updateGameState(_uiState.value.score)
        // Reset user guess
        updateUserGuess("")
    }

}