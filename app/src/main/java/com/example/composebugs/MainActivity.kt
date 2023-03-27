@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.composebugs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import com.example.composebugs.ui.theme.ComposeBugsTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var isLoading by remember { mutableStateOf(false) }
            LaunchedEffect(key1 = isLoading) {
                if (isLoading) {
                    delay(500L) // This delay allows time for focus to change to the dialog.  Removing or making it like 100 is basically the same as no dialog
                    isLoading = false
                }
            }
            ComposeBugsTheme {
                Scaffold {
                    Column(modifier = Modifier.padding(it)) {
                        LoadingDialog(showLoadingDialog = isLoading)
                        Text(
                            "Clickable Text",
                            modifier = Modifier.clickable {
                                isLoading = true
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun LoadingDialog(showLoadingDialog: Boolean) {
    if (showLoadingDialog) {
        Dialog(
            onDismissRequest = { }
        ) {
            Text("Dialog") // If we remove this Text the issue does not appear.  I assume due to this dialog not getting talkback focus
        }
    }
}
