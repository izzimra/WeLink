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
                        composable("post_from_request/{title}/{courseCode}") { backStackEntry ->
                            val title = backStackEntry.arguments?.getString("title") ?: ""
                            val courseCode = backStackEntry.arguments?.getString("courseCode") ?: ""
                            PostScreen(
                                navController = navController,
                                viewModel = viewModel,
                                prefilledTitle = title,
                                prefilledCourseCode = courseCode
                            )
                        }
                        composable("profile") {
                            ProfileScreen(navController = navController, viewModel = viewModel)
                        }
                        composable("leaderboard") {
                            LeaderboardScreen(navController = navController)
                        }
                        composable("request_detail/{title}/{courseCode}/{requester}") { backStackEntry ->
                            val title = backStackEntry.arguments?.getString("title") ?: ""
                            val courseCode = backStackEntry.arguments?.getString("courseCode") ?: ""
                            val requester = backStackEntry.arguments?.getString("requester") ?: ""
                            RequestDetailScreen(
                                navController = navController,
                                title = title,
                                courseCode = courseCode,
                                requester = requester
                            )
                        }
                    }
                }
            }
        }
    }
}