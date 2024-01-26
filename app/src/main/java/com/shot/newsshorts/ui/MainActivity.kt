package com.shot.newsshorts.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.shot.newsshorts.ui.navigation.AppNavigationGraph
import com.shot.newsshorts.ui.theme.NewsShortsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        installSplashScreen()
        setContent {
            NewsShortsTheme {
                Surface(modifier = Modifier.fillMaxSize()
                    .background(Color.White)) {

                    ShotNewsEntryPoint()
                }

            }
        }
    }
}

@Composable
fun ShotNewsEntryPoint(){
    AppNavigationGraph()
}