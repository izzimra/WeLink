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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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

    // Local input state for the material the user wants to share.
    var title by remember { mutableStateOf("") }
    var courseCode by remember { mutableStateOf("") }

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

            // Title input for the material to share.
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Material title") },
                placeholder = { Text("e.g. Calculus Chapter 3 Notes", fontSize = 12.sp) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(Modifier.height(8.dp))

            // Course code input.
            OutlinedTextField(
                value = courseCode,
                onValueChange = { courseCode = it },
                label = { Text("Course code") },
                placeholder = { Text("e.g. TTTK1143", fontSize = 12.sp) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(Modifier.height(8.dp))

            // Upload what the user typed to Firestore, then clear the fields.
            Button(
                onClick = {
                    if (title.isNotBlank() && courseCode.isNotBlank()) {
                        viewModel.share(
                            CommunityMaterial(
                                title = title.trim(),
                                courseCode = courseCode.trim(),
                                materialType = "Notes",
                                uploader = "Izzi (A207944)",
                                timestamp = System.currentTimeMillis()
                            )
                        )
                        title = ""        // clear so the next share is fresh
                        courseCode = ""
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Share to Community Cloud", fontWeight = FontWeight.Bold)
            }

            Spacer(Modifier.height(12.dp))

            if (materials.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxWidth().padding(32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text("No community materials yet. Type one above and share!",
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
