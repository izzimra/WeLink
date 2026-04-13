package com.example.a207944_izzi_izwan_lab02

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a207944_izzi_izwan_lab02.ui.theme.A207944_Izzi_Izwan_Lab02Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            A207944_Izzi_Izwan_Lab02Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HomeScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {

    var searchInput by remember { mutableStateOf("") }
    var submittedSearch by remember { mutableStateOf("") }


    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF4F7FF))
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Weather:", fontSize = 14.sp, fontWeight = FontWeight.Medium, color = Color.Gray)
                Column(modifier = Modifier.padding(start = 8.dp)) {
                    Row(verticalAlignment = Alignment.Bottom) {
                        Text(
                            text = "28°  ",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF1A1A2E)
                        )
                        Text(
                            text = "Hey Izzi",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF1A1A2E)
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "Loc: ", fontSize = 11.sp, color = Color.Gray)
                        Text(
                            text = "FTSM, UKM Bangi",
                            fontSize = 11.sp,
                            color = Color(0xFF888888)
                        )
                    }
                }
            }
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Color(0xFFC0A87A), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text("Me", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp)
                .background(Color.White, RoundedCornerShape(14.dp))
                .padding(horizontal = 12.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = searchInput,
                onValueChange = { searchInput = it },
                placeholder = {
                    Text(
                        text = "Search faculty or material",
                        fontSize = 12.sp,
                        color = Color(0xFFAAAAAA)
                    )
                },
                modifier = Modifier.weight(1f),
                singleLine = true,
                shape = RoundedCornerShape(12.dp)
            )

            Box(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .background(Color(0xFF2979FF), RoundedCornerShape(12.dp))
                    .clickable { submittedSearch = searchInput }
                    .padding(horizontal = 16.dp, vertical = 14.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Search", fontSize = 14.sp, color = Color.White, fontWeight = FontWeight.Bold)
            }
        }

        if (submittedSearch.isNotEmpty()) {
            Text(
                text = "Displaying results for: \"$submittedSearch\"",
                color = Color(0xFF2979FF),
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(top = 8.dp, start = 4.dp)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 14.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(80.dp)
                    .background(Color(0xFF2979FF), RoundedCornerShape(14.dp))
            )

            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(80.dp)
                    .background(Color.White, RoundedCornerShape(14.dp))
            )
        }


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 14.dp)
                .background(Color.White, RoundedCornerShape(20.dp))
                .padding(16.dp)
        ) {
            Column {
                Text(
                    text = "Material Contributions",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1A1A2E)
                )
                Text(
                    text = "Your sharing activity this year",
                    fontSize = 10.sp,
                    color = Color(0xFFAAAAAA),
                    modifier = Modifier.padding(top = 2.dp)
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp)
                        .height(110.dp)
                        .background(Color(0xFFF4F7FF), RoundedCornerShape(12.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Contribution Heat Map",
                        fontSize = 12.sp,
                        color = Color(0xFFAAAAAA)
                    )
                }
            }
        }


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            Box(
                modifier = Modifier
                    .weight(1.4f)
                    .height(140.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFFB3D4F5))
            ) {
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                        .background(Color(0xCCFFFFFF), RoundedCornerShape(8.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Save", fontSize = 10.sp, fontWeight = FontWeight.Medium)
                }
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .fillMaxWidth()
                        .background(Color(0xEEFFFFFF))
                        .padding(horizontal = 10.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = "FACULTY",
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF2979FF)
                    )
                    Text(
                        text = "FTSM",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1A1A2E)
                    )
                }
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(140.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFFA3D4B5))
            ) {
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .fillMaxWidth()
                        .background(Color(0xEEFFFFFF))
                        .padding(horizontal = 10.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = "FACULTY",
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF2979FF)
                    )
                    Text(
                        text = "FKAB",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1A1A2E)
                    )
                }
            }
        }

        Text(
            text = "Materials",
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1A1A2E),
            modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CategoryItem(shortText = "PY", label = "Past Year", bg = Color(0xFFE0F0FF))
            CategoryItem(shortText = "Note", label = "Notes", bg = Color(0xFFFFF0E0))
            CategoryItem(shortText = "Lab", label = "Lab Report", bg = Color(0xFFE0FFE8))
            CategoryItem(shortText = "Quiz", label = "Mock Quiz", bg = Color(0xFFFFE0F0))
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CategoryItem(shortText = "Tut", label = "Tutorial", bg = Color(0xFFF0E0FF))
            CategoryItem(shortText = "Slide", label = "Slides", bg = Color(0xFFFFE8E0))
            CategoryItem(shortText = "Code", label = "Code", bg = Color(0xFFE0F4FF))
            CategoryItem(shortText = "Grp", label = "Groups", bg = Color(0xFFE8FFE0))
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .background(Color.White, RoundedCornerShape(20.dp))
                .padding(16.dp)
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Student Requests",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1A1A2E)
                    )
                    Text(text = "View All", fontSize = 12.sp, color = Color(0xFF2979FF))
                }
                RequestRow(shortText = "PY", title = "Past Year TTTK1143", sub = "Attar · FTSM · 2h ago", xp = "+150 XP")
                RequestRow(shortText = "Lab", title = "Lab Report TTTE2113", sub = "Salsa · FTSM · 5h ago", xp = "+100 XP")
                RequestRow(shortText = "Note", title = "Notes TTTE2123", sub = "Bie · FTSM · 1d ago", xp = "+80 XP")
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .background(Color.White, RoundedCornerShape(20.dp))
                .padding(horizontal = 16.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Home", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color(0xFF2979FF))
                Box(
                    modifier = Modifier
                        .padding(top = 4.dp)
                        .size(5.dp)
                        .background(Color(0xFF2979FF), CircleShape)
                )
            }
            Text(text = "Favs", fontSize = 14.sp, fontWeight = FontWeight.Medium, color = Color.Gray)
            Text(text = "Post", fontSize = 14.sp, fontWeight = FontWeight.Medium, color = Color.Gray)
            Text(text = "Alerts", fontSize = 14.sp, fontWeight = FontWeight.Medium, color = Color.Gray)
            Text(text = "Chat", fontSize = 14.sp, fontWeight = FontWeight.Medium, color = Color.Gray)
        }
    }
}

@Composable
fun CategoryItem(shortText: String, label: String, bg: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(54.dp)
                .background(bg, RoundedCornerShape(14.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(text = shortText, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1A1A2E))
        }
        Text(
            text = label,
            fontSize = 10.sp,
            color = Color(0xFF555555),
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

@Composable
fun RequestRow(shortText: String, title: String, sub: String, xp: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(38.dp)
                .background(Color(0xFFE8F0FF), RoundedCornerShape(10.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(text = shortText, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color(0xFF2979FF))
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 10.dp)
        ) {
            Text(text = title, fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF1A1A2E))
            Text(text = sub,   fontSize = 10.sp, color = Color(0xFF888888))
        }
        Box(
            modifier = Modifier
                .background(Color(0xFFE8F0FF), RoundedCornerShape(8.dp))
                .padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            Text(text = xp, fontSize = 9.sp, fontWeight = FontWeight.Bold, color = Color(0xFF2979FF))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    A207944_Izzi_Izwan_Lab02Theme {
        HomeScreen()
    }
}