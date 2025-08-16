package com.gdgmmmut.app.presentation.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Home : Screen("home")
    object Events : Screen("events")
    object Team : Screen("team")
    object Resources : Screen("resources")
    object Quizzes : Screen("quizzes")
    object About : Screen("about")
}

sealed class BottomNavItem(val route: String, val icon: Int, val title: String) {
    object Home : BottomNavItem("home", android.R.drawable.ic_menu_home, "Home")
    object Events : BottomNavItem("events", android.R.drawable.ic_menu_today, "Events")
    object Team : BottomNavItem("team", android.R.drawable.ic_menu_myplaces, "Team")
    object Resources : BottomNavItem("resources", android.R.drawable.ic_menu_agenda, "Resources")
    object Quizzes : BottomNavItem("quizzes", android.R.drawable.ic_menu_help, "Quizzes")
}
