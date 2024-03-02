package com.demo.composeapplication.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.UrlAnnotation
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withAnnotation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType

@OptIn(ExperimentalTextApi::class)
@Composable
fun UseSpannableString() {
    val spanTag = "Android"
    val buildSpannableString = buildAnnotatedString {
        append("Hello")
        withAnnotation(UrlAnnotation("https://www.google.com")) {
            withStyle(SpanStyle(color = Color.Blue, textDecoration = TextDecoration.Underline)) {
                append(" from")
            }
        }
        appendInlineContent("id")
        withStyle(SpanStyle(fontSize = TextUnit(28f, TextUnitType.Sp))){
            append(" Spannable")
        }
        withStyle(SpanStyle(fontWeight = FontWeight.Bold)){
            append(" String")
        }
        withStyle(SpanStyle(fontStyle = FontStyle.Italic, textDecoration = TextDecoration.Underline)){
            append(" String")
        }
        withAnnotation(spanTag, spanTag) {
            withStyle(SpanStyle(color = Color.Blue, textDecoration = TextDecoration.Underline)){
                append(" Android")
            }
        }
        withStyle(SpanStyle(brush = Brush.horizontalGradient(colors = listOf(Color.Red, Color.Blue, Color.Yellow, Color.Green)))) {
            append("\nHello from multiple colors")
        }
    }
    val uriHandler = LocalUriHandler.current
    val context = LocalContext.current
    Column {
        Text(text = buildSpannableString, inlineContent = mapOf("id" to InlineTextContent(Placeholder(
            TextUnit(12f, TextUnitType.Sp), TextUnit(12f, TextUnitType.Sp), PlaceholderVerticalAlign.Center
        )){
            Icon(imageVector = Icons.Outlined.Home, contentDescription = "home")
        }))
        ClickableText(text = buildSpannableString, onClick = {
            buildSpannableString.getUrlAnnotations(it, it).getOrNull(0)?.let { urlAnnotation ->
                uriHandler.openUri(urlAnnotation.item.url)
            }
            buildSpannableString.getStringAnnotations(spanTag, it, it).getOrNull(0)?.let { stringAnnotation ->
                Toast.makeText(context, stringAnnotation.item, Toast.LENGTH_SHORT).show()
            }
        })
    }
}

@Preview
@Composable
fun PreviewUseSpannableString() {
    UseSpannableString()
}