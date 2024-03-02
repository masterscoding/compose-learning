package com.demo.composeapplication.data

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import com.demo.composeapplication.User

class ImageContent(private val user: User)  : ColumnContent{
    @Composable
    override fun BuildItem(action: (Any) -> Unit) {
        Icon(imageVector = user.userImage, contentDescription = user.userName)
    }

    override fun getId(): Int {
        return user.userId
    }
}