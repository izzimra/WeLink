package com.example.a207944_izzi_izwan_lab02

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.a207944_izzi_izwan_lab02.ui.theme.A207944_Izzi_Izwan_Lab03Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            A207944_Izzi_Izwan_Lab03Theme {
                val navController = rememberNavController()
                val viewModel: AppViewModel = viewModel()

                Surface(modifier = Modifier.fillMaxSize()) {
                    NavHost(navController = navController, startDestination = "home") {
                        composable("home") {
                            HomeScreen(navController = navController, viewModel = viewModel)
                        }
                        composable("post") {
                            PostScreen(navController = navController, viewModel = viewModel)
                        }
                        composable("profile") {
                            ProfileScreen(navController = navController, viewModel = viewModel)
                        }
                    }
                }
            }
        }
    }
}
