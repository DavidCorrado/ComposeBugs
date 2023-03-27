package com.example.composebugs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.composebugs.ui.theme.ComposeBugsTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBugsTheme {
                var pageShown by remember { mutableStateOf("1") }

                LaunchedEffect(key1 = pageShown) {
                    if (pageShown == "1") {
                        delay(2500)
                        pageShown = "2"
                    } else if (pageShown == "2") {
                        delay(2500)
                        pageShown = "3"
                    }
                }
                when (pageShown) {
                    "1" -> {
                        Text("Page 1")
                    }
                    "2" -> {
                        Text("Page 2")
                    }
                    else -> {
                        Text("Page 3")
                    }
                }
            }
        }
    }
}
