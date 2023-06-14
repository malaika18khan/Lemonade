package com.androidcodelabs.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.androidcodelabs.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                LemonApp()

            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LemonApp(){
    var currentStep by remember { mutableStateOf(1) }
    var squeezeStep by remember { mutableStateOf(0)}

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Lemonade",
                        fontWeight = FontWeight.Bold
                    ) },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { innerpadding ->

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        when (currentStep) {
            1 -> {
                LemonTextWithImage(
                    imageCode = R.drawable.lemon_tree,
                    text = R.string.tap_to_select,
                    contentDesc = R.string.lemon_tree_cd,
                    onImageClick = {
                        squeezeStep = (2..4).random()
                        currentStep = 2
                    })
            }
            2 -> {
                LemonTextWithImage(
                    imageCode = R.drawable.lemon_squeeze,
                    text = R.string.keep_tapping,
                    contentDesc = R.string.lemon_cd,
                    onImageClick = {
                        squeezeStep--
                        if (squeezeStep == 0) {
                            currentStep = 3
                        }
                    })
            }
            3 -> {
                LemonTextWithImage(
                    imageCode = R.drawable.lemon_drink,
                    text = R.string.tap_to_drink,
                    contentDesc = R.string.glass_of_lemonade_cd,
                    onImageClick = {
                        currentStep = 4
                    })
            }
            4 -> {
                LemonTextWithImage(
                    imageCode = R.drawable.lemon_restart,
                    text = R.string.start_again,
                    contentDesc = R.string.empty_glass_cd,
                    onImageClick = {
                        currentStep = 1
                    })
            }

        }
    }
        
    }
}

@Composable
fun LemonTextWithImage(imageCode: Int, text: Int,
                       contentDesc: Int, onImageClick: () -> Unit,
                       modifier: Modifier = Modifier) {
    Box(modifier = Modifier) {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = onImageClick,
                shape = RoundedCornerShape(dimensionResource(R.dimen.button_corner_radius)),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
            ) {
                Image(
                    painter = painterResource(imageCode),
                    contentDescription = stringResource(contentDesc),
                    modifier = Modifier
                        .width(dimensionResource(R.dimen.button_image_width))
                        .height(dimensionResource(R.dimen.button_image_height))
                        .padding(dimensionResource(R.dimen.button_interior_padding))

                )
            }

            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_vertical)))
            Text(
                text = stringResource(text),
                fontSize = 18.sp,
                style = MaterialTheme.typography.bodyLarge
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LemonadeTheme {
        LemonApp()
    }
}