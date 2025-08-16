package com.gdgmmmut.app.presentation.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.gdgmmmut.app.presentation.navigation.BottomNavItem
import com.gdgmmmut.app.ui.theme.GoogleBlue
import com.gdgmmmut.app.ui.theme.GoogleGreen
import com.gdgmmmut.app.ui.theme.GoogleRed
import com.gdgmmmut.app.ui.theme.GoogleYellow
import com.gdgmmmut.app.ui.theme.PhonePeGray
import com.gdgmmmut.app.ui.theme.White

@Composable
fun HomeScreen(navController: NavController) {
    var selectedItem by remember { mutableIntStateOf(0) }
    
    val bottomNavItems = listOf(
        BottomNavItem.Home,
        BottomNavItem.Events,
        BottomNavItem.Team,
        BottomNavItem.Resources,
        BottomNavItem.Quizzes
    )
    
    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = White,
                tonalElevation = 8.dp
            ) {
                bottomNavItems.forEachIndexed { index, item ->
                    val isSelected = selectedItem == index
                    val scale by animateFloatAsState(
                        targetValue = if (isSelected) 1.2f else 1f,
                        animationSpec = tween(200),
                        label = "icon_scale"
                    )
                    
                    NavigationBarItem(
                        icon = {
                            Icon(
                                painter = painterResource(id = item.icon),
                                contentDescription = item.title,
                                modifier = Modifier.scale(scale)
                            )
                        },
                        label = {
                            Text(
                                text = item.title,
                                style = MaterialTheme.typography.labelSmall
                            )
                        },
                        selected = isSelected,
                        onClick = {
                            selectedItem = index
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = when (index) {
                                0 -> GoogleBlue
                                1 -> GoogleRed
                                2 -> GoogleYellow
                                3 -> GoogleGreen
                                else -> GoogleBlue
                            },
                            unselectedIconColor = PhonePeGray,
                            selectedTextColor = when (index) {
                                0 -> GoogleBlue
                                1 -> GoogleRed
                                2 -> GoogleYellow
                                3 -> GoogleGreen
                                else -> GoogleBlue
                            },
                            unselectedTextColor = PhonePeGray,
                            indicatorColor = Color.Transparent
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (selectedItem) {
                0 -> HomeContent()
                1 -> EventsScreen()
                2 -> TeamScreen()
                3 -> ResourcesScreen()
                4 -> QuizzesScreen()
            }
        }
    }
}

@Composable
fun HomeContent() {
    HomeContentDetailed()
}

@Composable
fun EventsScreen() {
    EventsDetailedScreen()
}

@Composable
fun TeamScreen() {
    Text("Team Screen Coming Soon!")
}

@Composable
fun ResourcesScreen() {
    Text("Resources Screen Coming Soon!")
}

@Composable
fun QuizzesScreen() {
    Text("Quizzes Screen Coming Soon!")
}
