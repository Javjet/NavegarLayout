package com.iessanalberto.dam2.javiet.navegarlayout.specificControls


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import com.iessanalberto.dam2.javiet.navegarlayout.CientificasLista
import com.iessanalberto.dam2.javiet.navegarlayout.R

//https://www.youtube.com/watch?v=1KDNvKvsDh4


class ExposedDropMenuStateHolder {

    var enabled by mutableStateOf(false)
    var value by mutableStateOf("")
    var selectedIndex by mutableStateOf(-1)
    var size by mutableStateOf(Size.Zero)
    val icon:Int

    @Composable get() = if(enabled){
        R.drawable.ic_baseline_arrow_drop_up_24
    }else {
        R.drawable.ic_baseline_arrow_drop_down_24
    }

    val items = CientificasLista.map { "${it.nombre}" }

    fun onEnabled(newValue:Boolean){
        enabled=newValue
    }

     fun onSelectedIndex(newValue: Int){
         selectedIndex=newValue
    }

    fun onSize(newValue: Size){
        size=newValue

    }


}
@Composable
fun rememberExposedMenuStateHolder() = remember{
    ExposedDropMenuStateHolder()
}