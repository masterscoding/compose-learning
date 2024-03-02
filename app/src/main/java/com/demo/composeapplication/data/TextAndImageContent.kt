package com.demo.composeapplication.data

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.demo.composeapplication.User

class TextAndImageContent(private val user: User) : ColumnContent {
    @Composable
    override fun BuildItem(action: (Any) -> Unit) {
        Row {
            Icon(imageVector = user.userImage, contentDescription = user.userName)
            Text(text= user.userName)
        }
    }

    override fun getId(): Int {
        return user.userId
    }
}