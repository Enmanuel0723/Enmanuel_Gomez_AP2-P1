package com.example.enmanuel_gomez_ap2_p1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.enmanuel_gomez_ap2_p1.navigation.AppNavHost
import com.example.enmanuel_gomez_ap2_p1.ui.theme.Enmanuel_Gomez_AP2_P1Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Enmanuel_Gomez_AP2_P1Theme {
                AppNavHost()
            }
        }
    }
}
