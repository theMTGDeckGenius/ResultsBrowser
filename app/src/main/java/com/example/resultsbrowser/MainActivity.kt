package com.example.resultsbrowser

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.resultsbrowser.ui.screens.SportsResultsScreen
import com.example.resultsbrowser.ui.theme.ResultsBrowserTheme
import com.example.resultsbrowser.viewmodels.SportsResultsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    var isReload = false

    private val sportsResultsViewModel: SportsResultsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ResultsBrowserTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SportsResultsScreen(
                        sportsResultsViewModel = sportsResultsViewModel
                    )
                }
            }
        }
    }
}