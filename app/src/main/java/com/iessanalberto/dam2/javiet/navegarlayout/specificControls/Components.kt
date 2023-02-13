package com.iessanalberto.dam2.javiet.navegarlayout.specificControls

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.iessanalberto.dam2.javiet.navegarlayout.R
import com.iessanalberto.dam2.javiet.navegarlayout.ui.GameViewModel

@Composable
fun ExposedDropdownMenu(stateHolder: ExposedDropMenuStateHolder, gameViewModel: GameViewModel) {

    /*Box(contentAlignment = Alignment.Center) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {*/


            /*OutlinedTextField(
                value = stateHolder.value,
                onValueChange = {},
                label = { Text(text = "Label") },
                trailingIcon = {
                    Icon(painter = painterResource(id = stateHolder.icon),
                        contentDescription = null,
                        Modifier.clickable { stateHolder.onEnabled(!(stateHolder.enabled)) }
                    )
                },
                modifier = Modifier.onGloballyPositioned {
                    stateHolder.onSize(it.size.toSize())
                }
            )*/


            DropdownMenu(
                offset = DpOffset(x = (-700).dp, y = (-10).dp),
                expanded = stateHolder.enabled,

                onDismissRequest = {
                    stateHolder.onEnabled(false)
                },
                modifier = Modifier

                    //.fillMaxWidth(0.7f)
                    .fillMaxHeight(0.3f)
                    .width(with(LocalDensity.current) { stateHolder.size.width.toDp() })
            ) {
                stateHolder.items.forEachIndexed { index, s ->
                    DropdownMenuItem(
                        onClick = {
                            stateHolder.onSelectedIndex(index)
                            gameViewModel.updateUserGuess(s)
                            stateHolder.onEnabled(false)
                        })
                    {
                        Text(text = s)
                    }
                }
            }
        /*}
    }*/
}