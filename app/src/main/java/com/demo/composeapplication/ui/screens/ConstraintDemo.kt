package com.demo.composeapplication.ui.screens

import android.content.res.Configuration
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet

@Composable
fun ComposeDemo() {
    val margin = if(LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE) 32.dp else 16.dp
    ConstraintLayout(getConstraintSet(margin)) {
        
        Text(text = "First View", modifier = Modifier.layoutId("text1"))
        Text(text = "Second View", modifier = Modifier.layoutId("text2"))
        Text(text = "Third View", modifier = Modifier.layoutId("text3"))
        Text(text = "Fourth View", modifier = Modifier.layoutId("text4"))
    }
}


fun getConstraintSet(margin: Dp): ConstraintSet {
    return ConstraintSet {
        val text1Ref = createRefFor("text1")
        val text2Ref = createRefFor("text2")
        val text3Ref = createRefFor("text3")
        val text4Ref = createRefFor("text4")

        val guidelineStart = createGuidelineFromStart(8.dp)
        val guidelineEnd = createGuidelineFromEnd(0.1f)
        val barrierBottom = createBottomBarrier(text1Ref, text2Ref, text3Ref)

        val horizontalChain = createHorizontalChain(text1Ref, text2Ref, text3Ref, chainStyle = ChainStyle.Packed)
        //val verticalChain = createVerticalChain(text1Ref, text2Ref, text3Ref, chainStyle = ChainStyle.Packed)

        constrain(horizontalChain) {
            start.linkTo(guidelineStart, margin)
            end.linkTo(guidelineEnd)
        }
        /*constrain(verticalChain) {
        }*/

       /* constrain(text2Ref) {
            top.linkTo(parent.top)
            start.linkTo(text1Ref.end)
        }

        constrain(text3Ref) {
            top.linkTo(parent.top)
            start.linkTo(text2Ref.end)
        }*/

        constrain(text4Ref) {
            top.linkTo(barrierBottom)
            start.linkTo(text1Ref.start)
        }
    }
}

@Preview
@Composable
fun PreviewComposeDemo() {
    ComposeDemo()
}
@Preview(device = "spec:parent=pixel_5,orientation=landscape")
@Composable
fun PreviewComposeDemoLandscape() {
    ComposeDemo()
}