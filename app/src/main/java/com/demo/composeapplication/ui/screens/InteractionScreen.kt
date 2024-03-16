package com.demo.composeapplication.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun InteractionContent() {
    val interactionSource = remember {
        MutableInteractionSource()
    }
    val focusState = interactionSource.collectIsFocusedAsState()
    val pressState = interactionSource.collectIsPressedAsState()

    Row(modifier = Modifier
        .background(color = if(pressState.value) Color.Red else Color.Transparent)
        .border(
            width = if (focusState.value) 1.dp else 0.dp,
            color = if (focusState.value) Color.Blue else Color.Transparent
        )
        .clickable(interactionSource = interactionSource, indication = rememberRipple(), onClick = {

        })
        .focusable(interactionSource = interactionSource)
        .hoverable(interactionSource)
    ) {
        Text(text = "Click Me", modifier = Modifier.padding(16.dp))
    }
}

@Preview
@Composable
fun PreviewInteractionContent() {
    InteractionContent()
}