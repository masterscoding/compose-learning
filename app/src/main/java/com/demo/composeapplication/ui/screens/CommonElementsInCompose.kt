package com.demo.composeapplication.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun DisplayCommonContents() {
    Column(modifier = Modifier.background(Color.LightGray)) {
        Card(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(100.dp), shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 12.dp),
            border = CardDefaults.outlinedCardBorder()) {
            Text(text = "Solid card", modifier = Modifier.padding(16.dp))

            val annotationString = buildAnnotatedString {
                append("Test")
                withStyle(SpanStyle(Color.Red)){
                    append(" spannable String")
                }
            }
            Button(onClick = {}) {
                Text(annotationString)
            }

        }

        OutlinedCard(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(250.dp),
            shape = CutCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.Red, contentColor = Color.Yellow)
            ,
        ) {
            Text(text = "Outline card", modifier = Modifier.padding(16.dp))
            FilledTonalButton(onClick = { /*TODO*/ }) {
                Text(text = "Tonal")
            }
            OutlinedButton(onClick = { /*TODO*/ }) {
                Text(text = "Tonal")
            }
            ElevatedButton(onClick = { /*TODO*/ }) {
                Text(text = "Tonal")
            }
            TextButton(onClick = { /*TODO*/ }) {
                Text(text = "Tonal")
            }
        }

        ElevatedCard(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(100.dp)) {
            Text(text = "Elevated card", modifier = Modifier.padding(16.dp))
            Row {
                AssistChip(onClick = { /*TODO*/ }, label = { Text(text = "Assist") }, leadingIcon = {
                    Icon(imageVector = Icons.Outlined.AccountBox, contentDescription = "Account")
                })
                FilterChip(onClick = { /*TODO*/ }, selected = false, label = { Text(text = "Assist") }, leadingIcon = {
                    Icon(imageVector = Icons.Outlined.AccountBox, contentDescription = "Account")
                })
                InputChip(selected = true, onClick = { /*TODO*/ }, label = { Text(text = "Assist") })
                SuggestionChip(onClick = { /*TODO*/ }, label = { Text(text = "Assist") })
            }
        }
    }
}

@Preview
@Composable
fun PreviewDisplayCommonContents() {
     DisplayCommonContents()
}
