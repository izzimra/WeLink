package com.example.a207944_izzi_izwan_lab02

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun CommunityFeedScreen(
    navController: NavController,
    // viewModel() builds CommunityViewModel directly (no factory needed).
    viewModel: CommunityViewModel = viewModel()
) {
    // collectAsState subscribes to the cloud StateFlow; recomposes when new data arrives.
    val materials by viewModel.materials.collectAsState()

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Text("Community Feed", fontSize = 20.sp, fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface)
            Text("Materials shared by students everywhere", fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant)

            Spacer(Modifier.height(12.dp))

            // Temporary test button: pushes a sample doc so we can prove the cloud round-trip
            // before the Camera/API flow is wired in. We'll replace this on Day 3.
            Button(
                onClick = {
                    viewModel.share(
                        CommunityMaterial(
                            title = "Sample Material",
                            courseCode = "TTTK1143",
                            materialType = "Notes",
                            uploader = "Izzi (A207944)",
                            timestamp = System.currentTimeMillis()
                        )
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Share a test material to cloud", fontWeight = FontWeight.Bold)
            }

            Spacer(Modifier.height(12.dp))

            if (materials.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxWidth().padding(32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text("No community materials yet. Tap the button above!",
                        fontSize = 13.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            } else {
                // LazyColumn only renders visible rows (recycles), unlike a scrolling Column.
                LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    items(materials) { material ->
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            elevation = CardDefaults.cardElevation(0.dp),
                            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface)
                        ) {
                            Column(modifier = Modifier.padding(14.dp)) {
                                Text(material.title, fontSize = 14.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = MaterialTheme.colorScheme.onSurface)
                                Text("${material.courseCode} · ${material.materialType}",
                                    fontSize = 12.sp,
                                    color = MaterialTheme.colorScheme.primary)
                                Text("by ${material.uploader}", fontSize = 11.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant)
                            }
                        }
                    }
                }
            }

            Spacer(Modifier.height(12.dp))
            OutlinedButton(
                onClick = { navController.navigate("home") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            ) { Text("Back to Home") }
        }
    }
}
