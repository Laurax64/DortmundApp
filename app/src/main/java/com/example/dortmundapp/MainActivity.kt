package com.example.dortmundapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.dortmundapp.ui.DortmundApp
import com.example.dortmundapp.ui.theme.DortmundTheme

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DortmundTheme {
                Surface {
                    val windowSize = calculateWindowSizeClass(this)

                    DortmundApp(
                        windowSize = windowSize.widthSizeClass,
                    )
                }
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