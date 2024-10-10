package com.example.demo

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.demo.ui.theme.DemoTheme
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DemoTheme {
                AdaptiveApp()
            }
        }
    }
}

@Composable
fun AdaptiveApp() {
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    BoxWithConstraints {
        when {
            maxWidth < 480.dp -> { // Small screen (phones)
                SmallScreenLayout(isLandscape)
            }
            maxWidth >= 480.dp && maxWidth < 768.dp -> { // Medium screen (tablets)
                MediumScreenLayout(isLandscape)
            }
            else -> { // Large screen (tablets, foldables)
                LargeScreenLayout(isLandscape)
            }
        }
    }
}

//Three screen sizes' presets

@Composable
fun SmallScreenLayout(isLandscape: Boolean) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Small Screen Layout", fontSize = 20.sp, modifier = Modifier.padding(16.dp))
        if (isLandscape) {
            Row {
                ResponsiveImage()
                ResponsiveBoxes(isLandscape)
            }
        } else{
            ResponsiveImage()
            ResponsiveBoxes(isLandscape)
        }
        ResponsiveButton()
        ResponsiveList()
    }
}

@Composable
fun MediumScreenLayout(isLandscape: Boolean) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Medium Screen Layout", fontSize = 24.sp, modifier = Modifier.padding(16.dp))
        if (isLandscape) {
            Row {
                ResponsiveImage()
                ResponsiveBoxes(isLandscape)
            }
        } else{
            ResponsiveImage()
            ResponsiveBoxes(isLandscape)
        }
        ResponsiveButton()
        ResponsiveList()
    }
}

@Composable
fun LargeScreenLayout(isLandscape: Boolean) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Large Screen Layout", fontSize = 28.sp)
        if (isLandscape) {
            Row {
                ResponsiveImage()
                ResponsiveBoxes(isLandscape)
            }
        } else{
            ResponsiveImage()
            ResponsiveBoxes(isLandscape)
        }

        ResponsiveButton()
        ResponsiveList()
    }
}


//a button that can remember after switching between different screen size and orientations
@Composable
fun ResponsiveButton(modifier: Modifier = Modifier) {
    var clickCount by rememberSaveable { mutableStateOf(0) }
    Button(
        onClick = { clickCount++ },
        modifier = modifier
            .width(200.dp)
            .padding(16.dp)
    ) {
        Text(text = "Clicked $clickCount times")
    }
}


//several boxes based on orientation
@Composable
fun ResponsiveBoxes(isLandscape: Boolean) {
    if (isLandscape) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .padding(16.dp)
                    .aspectRatio(1f)
                    .border( 5.dp, color = Color.Black),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Box 1")
            }
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .padding(16.dp)
                    .aspectRatio(1f)
                    .border( 5.dp, color = Color.Black),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Box 2")
            }
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .padding(16.dp)
                    .aspectRatio(1f)
                    .border( 5.dp, color = Color.Black),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Box 3")
            }
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .padding(16.dp)
                    .aspectRatio(1f)
                    .border( 5.dp, color = Color.Black),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Box 1")
            }
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .padding(16.dp)
                    .aspectRatio(1f)
                    .border( 5.dp, color = Color.Black),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Box 2")
            }
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .padding(16.dp)
                    .aspectRatio(1f)
                    .border( 5.dp, color = Color.Black),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Box 3")
            }
        }
    }
}


// lazy list
@Composable
fun ResponsiveList(modifier: Modifier = Modifier) {
    val itemsList = (1..20).map { "Item $it" }

    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        items(itemsList) { item ->
            Text(text = item, fontSize = 18.sp, modifier = Modifier.padding(8.dp))
        }
    }
}


// responsive image based on orientation
@Composable
fun ResponsiveImage() {
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    Image(
        painter = painterResource(id = R.drawable.sample_image),
        contentDescription = "Sample Image",
        modifier = Modifier
            .size(if (isLandscape) 200.dp else 100.dp)
            .padding(16.dp)
    )
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DemoTheme {
        AdaptiveApp()
    }
}





