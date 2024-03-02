package com.demo.composeapplication.data

import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.demo.composeapplication.User

class CardContent(private val user: User) : ColumnContent {
    @Composable
    override fun BuildItem(action: (Any) -> Unit) {
        Card {
            Button(onClick = { action(user) }) {
                Text(text = user.userName)
            }
        }
    }

    override fun getId(): Int {
        return user.userId
    }
}