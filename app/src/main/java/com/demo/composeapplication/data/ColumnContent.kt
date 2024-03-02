package com.demo.composeapplication.data

import androidx.compose.runtime.Composable

interface ColumnContent {
    @Composable
    fun BuildItem(action: (Any) -> Unit)

    fun getId() : Int
}