package com.example.submisiondicoding.navigation

import androidx.compose.ui.graphics.vector.ImageVector

// layout item
data class NavigationItem(
    val title: String,
    val icon: ImageVector,
    val screen: Screen
)