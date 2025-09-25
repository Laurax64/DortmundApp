package com.example.dortmundapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.dortmundapp.ui.DortmundApp
import com.example.dortmundapp.ui.theme.DortmundTheme

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DortmundTheme {
                val windowSize = calculateWindowSizeClass(this)
                DortmundApp(
                    modifier = Modifier.statusBarsPadding(),
                    windowSize = windowSize.widthSizeClass,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DortmundAppCompactPreview() {
    DortmundTheme {
        DortmundApp(
            windowSize = WindowWidthSizeClass.Compact,
        )
    }
}

@Preview(showBackground = true, widthDp = 700)
@Composable
fun DortmundAppMediumPreview() {
    DortmundTheme {
        DortmundApp(
            windowSize = WindowWidthSizeClass.Medium,
        )
    }
}

@Preview(showBackground = true, widthDp = 1000)
@Composable
fun DortmundAppExpandedPreview() {
    DortmundTheme {
        DortmundApp(
            windowSize = WindowWidthSizeClass.Expanded,
        )
    }
}