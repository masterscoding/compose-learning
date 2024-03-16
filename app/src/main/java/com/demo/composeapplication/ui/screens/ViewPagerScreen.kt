package com.demo.composeapplication.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerSnapDistance
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalPagerScreen() {
    val pagerState = rememberPagerState {
        10
    }
    var displayedPagePosition by remember {
        mutableIntStateOf(0)
    }
    LaunchedEffect(key1 = pagerState) {
        snapshotFlow {
            pagerState.currentPage
        }.collect{
            displayedPagePosition = it
        }
    }
    val scope = rememberCoroutineScope()
    Column {
        Text(text = "Current page is $displayedPagePosition")
        Button(onClick = {
            scope.launch {
                var currentPage = pagerState.currentPage
                if(currentPage > 0) {
                    currentPage--
                }
                pagerState.scrollToPage(currentPage)
            }
        }) {
            Text(text = "Previous")
        }
        Button(onClick = {
            scope.launch {
                var currentPage = pagerState.currentPage
                if(currentPage < 10) {
                    currentPage++
                }
                pagerState.scrollToPage(currentPage)
            }
        }) {
            Text(text = "Next")
        }

        Box {
            HorizontalPager(state = pagerState,
                contentPadding = PaddingValues(50.dp),
                pageSpacing = 20.dp, modifier = Modifier,
                flingBehavior = PagerDefaults.flingBehavior(state = pagerState, pagerSnapDistance = PagerSnapDistance.atMost(4))) {
                Row(modifier = Modifier
                    .background(Color.Red)
                    .fillMaxSize(), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Page ${pagerState.currentPage}")
                }
            }
            Row(modifier = Modifier.align(Alignment.BottomCenter)) {
                repeat(10){
                    val color = if(pagerState.currentPage == it) Color.Black else Color.Gray
                    Box(
                        Modifier
                            .padding(5.dp)
                            .clip(CircleShape)
                            .background(color)
                            .size(18.dp)
                            )
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun VerticalPagerScreen() {
    val pagerState = rememberPagerState {
        10
    }
    var displayedPagePosition by remember {
        mutableIntStateOf(0)
    }
    LaunchedEffect(key1 = pagerState) {
        snapshotFlow {
            pagerState.currentPage
        }.collect{
            displayedPagePosition = it
        }
    }
    val scope = rememberCoroutineScope()
    Column {
        Text(text = "Current page is $displayedPagePosition")
        Button(onClick = {
            scope.launch {
                var currentPage = pagerState.currentPage
                if(currentPage > 0) {
                    currentPage--
                }
                pagerState.scrollToPage(currentPage)
            }
        }) {
            Text(text = "Previous")
        }
        Button(onClick = {
            scope.launch {
                var currentPage = pagerState.currentPage
                if(currentPage < 10) {
                    currentPage++
                }
                pagerState.scrollToPage(currentPage)
            }
        }) {
            Text(text = "Next")
        }

        Box {
            VerticalPager(state = pagerState,
                contentPadding = PaddingValues(50.dp),
                pageSpacing = 20.dp, modifier = Modifier,
                flingBehavior = PagerDefaults.flingBehavior(state = pagerState, pagerSnapDistance = PagerSnapDistance.atMost(4))) {
                Row(modifier = Modifier
                    .background(Color.Red)
                    .fillMaxSize(), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Page ${pagerState.currentPage}")
                }
            }
            Row(modifier = Modifier.align(Alignment.BottomCenter)) {
                repeat(10){
                    val color = if(pagerState.currentPage == it) Color.Black else Color.Gray
                    Box(
                        Modifier
                            .padding(5.dp)
                            .clip(CircleShape)
                            .background(color)
                            .size(18.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewHorizontalPagerScreen() {
    HorizontalPagerScreen()
}
@Preview
@Composable
fun PreviewVerticalPagerScreen() {
    VerticalPagerScreen()
}