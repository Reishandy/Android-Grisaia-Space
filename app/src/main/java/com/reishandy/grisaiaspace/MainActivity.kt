package com.reishandy.grisaiaspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.reishandy.grisaiaspace.ui.theme.GrisaiaSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GrisaiaSpaceTheme {
                GrisaiaLayout()
            }
        }
    }
}

@Composable
fun GrisaiaLayout() {
    // Variables
    var index: Int by remember { mutableIntStateOf(0) }
    val (imageResID, nameResID, descriptionResID) = getResourceId(index)

    // Layout
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.padding(16.dp).padding(top = 10.dp)
        ) {
            ImageContainer(
                imageResID = imageResID,
                nameResID = nameResID,
                modifier = Modifier
                    .weight(8f)
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(30.dp))

            DescriptionContainer(
                nameResID = nameResID,
                descriptionResID = descriptionResID,
                modifier = Modifier.weight(2f).fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))

            ButtonContainer(
                onClickNext = { index = navigate(next = true, index) },
                onClickPrev = { index = navigate(next = false, index) },
                modifier = Modifier.weight(1f).fillMaxWidth()
            )
        }
    }
}

@Composable
fun ImageContainer(imageResID: Int, nameResID: Int, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        shadowElevation = 10.dp
    ) {
        Surface(
            modifier = Modifier.padding(24.dp)
        ) {
            Image(
                painter = painterResource(imageResID),
                contentDescription = stringResource(nameResID)
            )
        }
    }
}

@Composable
fun DescriptionContainer(nameResID: Int, descriptionResID: Int, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.surfaceContainerHigh
    ) {
        Column(
            modifier = modifier.padding(10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(nameResID),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = stringResource(descriptionResID),
                fontSize = 15.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun ButtonContainer(onClickNext: () -> Unit, onClickPrev: () -> Unit, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = onClickPrev,
            modifier = Modifier.weight(1f),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary
            )
        ) {
            Text("Prev")
        }

        Spacer(modifier = Modifier.weight(0.2f))

        Button(
            onClick = onClickNext,
            modifier = Modifier.weight(1f),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary
            )
        ) {
            Text("Next")
        }
    }
}

fun getResourceId(index: Int): Triple<Int, Int, Int> {
    val imageResID: Int = when(index) {
        0 -> R.drawable.sakaki_yumiko
        1 -> R.drawable.matsushima_michiru
        2 -> R.drawable.komine_sachi
        3 -> R.drawable.suou_amane
        4 -> R.drawable.irisu_makina
        else -> R.drawable.kazami_kazuki
    }

    val nameResID: Int = when(index) {
        0 -> R.string.name_yumiko
        1 -> R.string.name_michiru
        2 -> R.string.name_sachi
        3 -> R.string.name_amane
        4 -> R.string.name_makina
        else -> R.string.name_kazuki
    }

    val descriptionResID: Int = when(index) {
        0 -> R.string.description_yumiko
        1 -> R.string.description_michiru
        2 -> R.string.description_sachi
        3 -> R.string.description_amane
        4 -> R.string.description_makina
        else -> R.string.description_kazuki
    }

    return Triple(imageResID, nameResID, descriptionResID)
}

fun navigate(next: Boolean, index: Int): Int {
    var newIndex = index

    if (next) {
        newIndex += 1
    } else {
        newIndex -= 1
    }

    if (newIndex > 5) newIndex = 0
    if (newIndex < 0) newIndex = 5

    return newIndex
}

@Preview(showBackground = true)
@Composable
fun GrisaiaPreview() {
    GrisaiaSpaceTheme {
        GrisaiaLayout()
    }
}