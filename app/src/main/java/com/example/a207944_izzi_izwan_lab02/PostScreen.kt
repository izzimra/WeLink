package com.example.a207944_izzi_izwan_lab02

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun PostScreen(navController: NavController, viewModel: AppViewModel) {
    var name by remember { mutableStateOf("") }
    var matric by remember { mutableStateOf("") }
    var fakulti by remember { mutableStateOf("") }
    var title by remember { mutableStateOf("") }
    var courseCode by remember { mutableStateOf("") }

    // Category picker
    val categoryOptions = listOf(
        Triple("Past Year", "PY", 150),
        Triple("Notes", "Note", 80),
        Triple("Lab Report", "Lab", 100),
        Triple("Slides", "Slide", 70),
        Triple("Code", "Code", 120)
    )
    var selectedCategoryIndex by remember { mutableStateOf(1) }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {

            // Header
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(0.dp),
                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text("Post Material", fontSize = 15.sp, fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface)
                        Text("Share notes with others", fontSize = 11.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                    Box(
                        modifier = Modifier.size(40.dp)
                            .background(MaterialTheme.colorScheme.tertiaryContainer, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Me", fontSize = 12.sp, fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onTertiaryContainer)
                    }
                }
            }

            // User Info
            Card(
                modifier = Modifier.fillMaxWidth().padding(top = 12.dp),
                elevation = CardDefaults.cardElevation(0.dp),
                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface)
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text("YOUR INFO", fontSize = 10.sp, fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Spacer(Modifier.height(8.dp))
                    OutlinedTextField(
                        value = name, onValueChange = { name = it },
                        label = { Text("Name") }, modifier = Modifier.fillMaxWidth(),
                        singleLine = true, shape = RoundedCornerShape(12.dp)
                    )
                    Spacer(Modifier.height(8.dp))
                    OutlinedTextField(
                        value = matric, onValueChange = { matric = it },
                        label = { Text("Matric No") }, modifier = Modifier.fillMaxWidth(),
                        singleLine = true, shape = RoundedCornerShape(12.dp)
                    )
                    Spacer(Modifier.height(8.dp))
                    OutlinedTextField(
                        value = fakulti, onValueChange = { fakulti = it },
                        label = { Text("Fakulti") }, modifier = Modifier.fillMaxWidth(),
                        singleLine = true, shape = RoundedCornerShape(12.dp)
                    )
                }
            }

            // Material Details + Category
            Card(
                modifier = Modifier.fillMaxWidth().padding(top = 12.dp),
                elevation = CardDefaults.cardElevation(0.dp),
                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface)
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text("MATERIAL DETAILS", fontSize = 10.sp, fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Spacer(Modifier.height(8.dp))
                    OutlinedTextField(
                        value = title, onValueChange = { title = it },
                        label = { Text("Title") }, modifier = Modifier.fillMaxWidth(),
                        singleLine = true, shape = RoundedCornerShape(12.dp)
                    )
                    Spacer(Modifier.height(8.dp))
                    OutlinedTextField(
                        value = courseCode, onValueChange = { courseCode = it },
                        label = { Text("Course Code") }, modifier = Modifier.fillMaxWidth(),
                        singleLine = true, shape = RoundedCornerShape(12.dp)
                    )

                    Spacer(Modifier.height(12.dp))
                    Text("CATEGORY", fontSize = 10.sp, fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Spacer(Modifier.height(8.dp))

                    // Category chips
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        categoryOptions.forEachIndexed { index, (label, _, _) ->
                            FilterChip(
                                selected = selectedCategoryIndex == index,
                                onClick = { selectedCategoryIndex = index },
                                label = { Text(label, fontSize = 11.sp) },
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = MaterialTheme.colorScheme.primary,
                                    selectedLabelColor = MaterialTheme.colorScheme.onPrimary
                                )
                            )
                        }
                    }

                    Spacer(Modifier.height(12.dp))

                    // XP preview
                    val (_, _, xp) = categoryOptions[selectedCategoryIndex]
                    Surface(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("XP reward for this post", fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant)
                            Text("+$xp XP", fontSize = 13.sp, fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary)
                        }
                    }

                    Spacer(Modifier.height(12.dp))

                    Button(
                        onClick = {
                            if (title.isNotBlank() && courseCode.isNotBlank()) {
                                val (category, short, xpVal) = categoryOptions[selectedCategoryIndex]
                                // Save user info to ViewModel
                                viewModel.updateUser(name, matric, fakulti)
                                // Add the post to shared state
                                viewModel.addPost(
                                    MaterialPost(
                                        title = title,
                                        courseCode = courseCode,
                                        posterName = name,
                                        posterMatric = matric,
                                        posterFakulti = fakulti,
                                        category = category,
                                        categoryShort = short,
                                        xpValue = xpVal
                                    )
                                )
                                // Navigate to materials list to show the new item
                                navController.navigate("materials")
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Submit Post", fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}