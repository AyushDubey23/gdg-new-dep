package com.gdgmmmut.app.presentation.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.gdgmmmut.app.R
import com.gdgmmmut.app.presentation.navigation.Screen
import com.gdgmmmut.app.ui.theme.GoogleBlue
import com.gdgmmmut.app.ui.theme.GoogleGreen
import com.gdgmmmut.app.ui.theme.GoogleRed
import com.gdgmmmut.app.ui.theme.GoogleYellow
import com.gdgmmmut.app.ui.theme.White
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    val scale = remember { Animatable(0f) }
    val alpha = remember { Animatable(0f) }
    
    LaunchedEffect(key1 = true) {
        // Logo scale animation
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 800,
                easing = FastOutSlowInEasing
            )
        )
        
        // Text fade in animation
        alpha.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 600,
                delayMillis = 400
            )
        )
        
        // Navigate to home after animation
        delay(2000)
        navController.navigate(Screen.Home.route) {
            popUpTo(Screen.Splash.route) { inclusive = true }
        }
    }
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        GoogleBlue,
                        GoogleGreen
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // GDG Logo
            Image(
                painter = painterResource(id = R.drawable.gdg_logo),
                contentDescription = "GDG Logo",
                modifier = Modifier
                    .size(120.dp)
                    .scale(scale.value)
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // GDG MMMUT Text
            Text(
                text = "GDG MMMUT",
                style = MaterialTheme.typography.headlineLarge.copy(
                    color = White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp
                ),
                modifier = Modifier.alpha(alpha.value)
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = "Google Developer Group",
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = White.copy(alpha = 0.9f),
                    fontSize = 16.sp
                ),
                modifier = Modifier.alpha(alpha.value)
            )
            
            Spacer(modifier = Modifier.height(4.dp))
            
            Text(
                text = "Madan Mohan Malaviya University of Technology",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = White.copy(alpha = 0.8f),
                    fontSize = 14.sp
                ),
                modifier = Modifier.alpha(alpha.value)
            )
        }
    }
}
