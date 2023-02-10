package com.iessanalberto.dam2.javiet.navegarlayout.ui

import com.iessanalberto.dam2.javiet.navegarlayout.data.Cientificas

data class GameUiState(
    val currentScientificData: Cientificas? =null,
    val currentGuessedScientific: String? = currentScientificData?.nombre,
    val currentScientific: Int = 0,
    val score: Int = 0,
    val isGuessedScientificWrong: Boolean = false,
    val isGameOver: Boolean = false,
    val positionLearn: Int=0,
    val cluePosition: Int=1,
    val CompressView: Boolean=true
    )