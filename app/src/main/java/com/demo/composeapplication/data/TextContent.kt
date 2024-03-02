package com.demo.composeapplication.data

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.demo.composeapplication.User

class TextContent(private val user: User) : ColumnContent {
    @Composable
    override fun BuildItem(action: (Any) -> Unit) {
        Text(text = user.userName)
    }

    override fun getId(): Int {
        return user.userId
    }
}