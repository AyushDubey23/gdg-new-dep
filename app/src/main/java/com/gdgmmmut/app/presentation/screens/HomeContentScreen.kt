package com.gdgmmmut.app.presentation.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gdgmmmut.app.R
import com.gdgmmmut.app.data.model.Event
import com.gdgmmmut.app.ui.theme.*
// import com.valentinilk.shimmer.shimmer // Temporarily disabled

@Composable
fun HomeContentDetailed() {
    var isLoading by remember { mutableStateOf(true) }
    
    LaunchedEffect(Unit) {
        // Simulate loading delay
        kotlinx.coroutines.delay(2000)
        isLoading = false
    }
    
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        GoogleBlue.copy(alpha = 0.05f),
                        White
                    )
                )
            ),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            HeaderSection()
        }
        
        item {
            SearchBar()
        }
        
        item {
            if (isLoading) {
                BannerShimmer()
            } else {
                EventBannerSection()
            }
        }
        
        item {
            QuickActionsSection()
        }
        
        item {
            if (isLoading) {
                UpcomingEventsShimmer()
            } else {
                UpcomingEventsSection()
            }
        }
        
        item {
            AnnouncementsSection()
        }
    }
}

@Composable
fun HeaderSection() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "Hello, Developer! 👋",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = GoogleBlue
                )
            )
            Text(
                text = "Welcome to GDG MMMUT",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = PhonePeGray
                )
            )
        }
        
        IconButton(
            onClick = { /* Handle notifications */ },
            modifier = Modifier
                .size(48.dp)
                .background(
                    color = GoogleBlue.copy(alpha = 0.1f),
                    shape = CircleShape
                )
        ) {
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = "Notifications",
                tint = GoogleBlue
            )
        }
    }
}

@Composable
fun SearchBar() {
    OutlinedTextField(
        value = "",
        onValueChange = { },
        placeholder = {
            Text(
                text = "Search events, resources...",
                color = PhonePeGray
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                tint = PhonePeGray
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp)),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = GoogleBlue,
            unfocusedBorderColor = PhonePeLightGray
        ),
        singleLine = true
    )
}

@Composable
fun EventBannerSection() {
    val banners = remember {
        listOf(
            "Android Workshop" to GoogleGreen,
            "Cloud Study Jam" to GoogleBlue,
            "Flutter Bootcamp" to GoogleRed
        )
    }
    
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(horizontal = 4.dp)
    ) {
        items(banners) { (title, color) ->
            BannerCard(title = title, color = color)
        }
    }
}

@Composable
fun BannerCard(title: String, color: Color) {
    Card(
        modifier = Modifier
            .width(280.dp)
            .height(140.dp)
            .clickable { },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            color,
                            color.copy(alpha = 0.7f)
                        )
                    )
                ),
            contentAlignment = Alignment.CenterStart
        ) {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = White,
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Register Now",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = White.copy(alpha = 0.9f)
                    )
                )
            }
        }
    }
}

@Composable
fun QuickActionsSection() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                text = "Quick Actions",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                QuickActionItem("Events", GoogleRed, R.drawable.ic_events)
                QuickActionItem("Team", GoogleYellow, R.drawable.ic_team)
                QuickActionItem("Resources", GoogleGreen, R.drawable.ic_resources)
                QuickActionItem("Quiz", GoogleBlue, R.drawable.ic_quiz)
            }
        }
    }
}

@Composable
fun QuickActionItem(title: String, color: Color, iconRes: Int) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .background(
                    color = color.copy(alpha = 0.1f),
                    shape = CircleShape
                )
                .clickable { },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = title,
                tint = color,
                modifier = Modifier.size(24.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.bodySmall.copy(
                fontWeight = FontWeight.Medium
            )
        )
    }
}

@Composable
fun UpcomingEventsSection() {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Upcoming Events",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold
                )
            )
            TextButton(onClick = { }) {
                Text(
                    text = "View All",
                    color = GoogleBlue
                )
            }
        }
        
        Spacer(modifier = Modifier.height(12.dp))
        
        // Mock events
        val events = remember {
            listOf(
                Event(
                    id = "1",
                    title = "Android Development Workshop",
                    description = "Learn Android development basics",
                    date = "2024-01-15",
                    time = "10:00 AM",
                    location = "Tech Hub, MMMUT",
                    imageUrl = "",
                    isUpcoming = true,
                    registrationUrl = "",
                    tags = listOf("Android", "Workshop")
                ),
                Event(
                    id = "2",
                    title = "Flutter Study Jam",
                    description = "Build cross-platform apps with Flutter",
                    date = "2024-01-20",
                    time = "2:00 PM",
                    location = "Online",
                    imageUrl = "",
                    isUpcoming = true,
                    registrationUrl = "",
                    tags = listOf("Flutter", "Study Jam")
                )
            )
        }
        
        events.forEach { event ->
            EventCard(event = event)
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Composable
fun EventCard(event: Event) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Event image placeholder
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(
                        color = GoogleBlue.copy(alpha = 0.1f),
                        shape = RoundedCornerShape(8.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "📅",
                    fontSize = 24.sp
                )
            }
            
            Spacer(modifier = Modifier.width(12.dp))
            
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = event.title,
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = "${event.date} • ${event.time}",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = PhonePeGray
                    )
                )
                Text(
                    text = event.location,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = PhonePeGray
                    )
                )
            }
            
            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(
                    containerColor = GoogleBlue
                ),
                shape = RoundedCornerShape(8.dp),
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp)
            ) {
                Text(
                    text = "Register",
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }
    }
}

@Composable
fun AnnouncementsSection() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                text = "📢 Announcements",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
            
            Text(
                text = "Welcome to the new GDG MMMUT app! Stay updated with all our latest events and resources.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = PhonePeGray
                )
            )
        }
    }
}

@Composable
fun BannerShimmer() {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(horizontal = 4.dp)
    ) {
        items(3) {
            Box(
                modifier = Modifier
                    .width(280.dp)
                    .height(140.dp)
                    .background(
                        color = ShimmerColorLight,
                        shape = RoundedCornerShape(16.dp)
                    )
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = GoogleBlue
                )
            }
        }
    }
}

@Composable
fun UpcomingEventsShimmer() {
    Column {
        repeat(3) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .background(
                                color = ShimmerColorLight,
                                shape = RoundedCornerShape(8.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(20.dp),
                            color = GoogleBlue,
                            strokeWidth = 2.dp
                        )
                    }
                    
                    Spacer(modifier = Modifier.width(12.dp))
                    
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(0.7f)
                                .height(20.dp)
                                .background(
                                    color = ShimmerColorLight,
                                    shape = RoundedCornerShape(4.dp)
                                )
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(0.5f)
                                .height(16.dp)
                                .background(
                                    color = ShimmerColorLight,
                                    shape = RoundedCornerShape(4.dp)
                                )
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}
