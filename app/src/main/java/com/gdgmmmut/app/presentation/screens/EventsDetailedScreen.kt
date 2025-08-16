package com.gdgmmmut.app.presentation.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gdgmmmut.app.data.model.Event
import com.gdgmmmut.app.ui.theme.*

@Composable
fun EventsDetailedScreen() {
    var selectedTab by remember { mutableIntStateOf(0) }
    var searchText by remember { mutableStateOf("") }
    
    val tabs = listOf("Upcoming", "Past", "All")
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        GoogleBlue.copy(alpha = 0.05f),
                        White
                    )
                )
            )
            .padding(16.dp)
    ) {
        // Header
        Text(
            text = "GDG Events",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold,
                color = GoogleBlue
            ),
            modifier = Modifier.padding(bottom = 16.dp)
        )
        
        // Search Bar
        OutlinedTextField(
            value = searchText,
            onValueChange = { searchText = it },
            placeholder = {
                Text(
                    text = "Search events...",
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
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Tab Row
        TabRow(
            selectedTabIndex = selectedTab,
            containerColor = White,
            contentColor = GoogleBlue,
            indicator = { tabPositions ->
                if (selectedTab < tabPositions.size) {
                    TabRowDefaults.Indicator(
                        modifier = Modifier
                            .tabIndicatorOffset(tabPositions[selectedTab])
                            .clip(CircleShape),
                        color = GoogleBlue,
                        height = 3.dp
                    )
                }
            }
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTab == index,
                    onClick = { selectedTab = index },
                    text = {
                        Text(
                            text = title,
                            fontWeight = if (selectedTab == index) FontWeight.Bold else FontWeight.Normal,
                            color = if (selectedTab == index) GoogleBlue else PhonePeGray
                        )
                    }
                )
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Events List
        val events = remember {
            getSampleEvents().filter { event ->
                when (selectedTab) {
                    0 -> event.isUpcoming
                    1 -> !event.isUpcoming
                    else -> true
                }
            }
        }
        
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(events) { event ->
                EventDetailCard(
                    event = event,
                    onClick = { /* Handle event click */ }
                )
            }
        }
    }
}

@Composable
fun EventDetailCard(
    event: Event,
    onClick: () -> Unit
) {
    val cardElevation by animateFloatAsState(
        targetValue = 8f,
        label = "card_elevation"
    )
    
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(cardElevation.dp),
        colors = CardDefaults.cardColors(containerColor = White)
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            // Event Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = event.title,
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary
                        )
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = event.description,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = PhonePeGray
                        ),
                        maxLines = 2
                    )
                }
                
                // Status Badge
                StatusBadge(isUpcoming = event.isUpcoming)
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Event Details
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                EventDetailItem(
                    icon = Icons.Default.CalendarToday,
                    text = "${event.date} • ${event.time}",
                    color = GoogleBlue
                )
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            EventDetailItem(
                icon = Icons.Default.LocationOn,
                text = event.location,
                color = GoogleRed
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Tags
            if (event.tags.isNotEmpty()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    event.tags.take(3).forEach { tag ->
                        TagChip(tag = tag)
                    }
                }
                
                Spacer(modifier = Modifier.height(16.dp))
            }
            
            // Action Button
            Button(
                onClick = { /* Handle registration */ },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (event.isUpcoming) GoogleBlue else PhonePeGray
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = if (event.isUpcoming) "Register Now" else "View Details",
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }
        }
    }
}

@Composable
fun StatusBadge(isUpcoming: Boolean) {
    val (backgroundColor, textColor, text) = if (isUpcoming) {
        Triple(GoogleGreen.copy(alpha = 0.1f), GoogleGreen, "Upcoming")
    } else {
        Triple(PhonePeGray.copy(alpha = 0.1f), PhonePeGray, "Past")
    }
    
    Box(
        modifier = Modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.Bold,
                color = textColor
            )
        )
    }
}

@Composable
fun EventDetailItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    text: String,
    color: Color
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = color,
            modifier = Modifier.size(18.dp)
        )
        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall.copy(
                color = PhonePeGray,
                fontWeight = FontWeight.Medium
            )
        )
    }
}

@Composable
fun TagChip(tag: String) {
    Box(
        modifier = Modifier
            .background(
                color = GoogleYellow.copy(alpha = 0.1f),
                shape = RoundedCornerShape(6.dp)
            )
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(
            text = tag,
            style = MaterialTheme.typography.labelSmall.copy(
                color = GoogleYellow.copy(alpha = 0.8f),
                fontWeight = FontWeight.Medium,
                fontSize = 10.sp
            )
        )
    }
}

private fun getSampleEvents(): List<Event> {
    return listOf(
        Event(
            id = "1",
            title = "Android Development Workshop",
            description = "Learn the fundamentals of Android app development with Kotlin and Jetpack Compose",
            date = "2024-01-15",
            time = "10:00 AM",
            location = "Tech Hub, MMMUT Campus",
            imageUrl = "",
            isUpcoming = true,
            registrationUrl = "",
            tags = listOf("Android", "Kotlin", "Jetpack Compose")
        ),
        Event(
            id = "2",
            title = "Flutter Study Jam",
            description = "Build beautiful cross-platform applications using Flutter framework",
            date = "2024-01-20",
            time = "2:00 PM",
            location = "Online (Google Meet)",
            imageUrl = "",
            isUpcoming = true,
            registrationUrl = "",
            tags = listOf("Flutter", "Dart", "Mobile Development")
        ),
        Event(
            id = "3",
            title = "Google Cloud Platform Bootcamp",
            description = "Comprehensive introduction to GCP services and deployment strategies",
            date = "2024-01-25",
            time = "11:00 AM",
            location = "Auditorium, MMMUT",
            imageUrl = "",
            isUpcoming = true,
            registrationUrl = "",
            tags = listOf("GCP", "Cloud", "DevOps")
        ),
        Event(
            id = "4",
            title = "Machine Learning with TensorFlow",
            description = "Hands-on workshop on building ML models with TensorFlow and Python",
            date = "2023-12-10",
            time = "9:00 AM",
            location = "Computer Lab, MMMUT",
            imageUrl = "",
            isUpcoming = false,
            registrationUrl = "",
            tags = listOf("ML", "TensorFlow", "Python")
        ),
        Event(
            id = "5",
            title = "Web Development with React",
            description = "Modern web development techniques using React and Node.js",
            date = "2023-12-15",
            time = "3:00 PM",
            location = "Seminar Hall, MMMUT",
            imageUrl = "",
            isUpcoming = false,
            registrationUrl = "",
            tags = listOf("React", "JavaScript", "Web Development")
        )
    )
}
