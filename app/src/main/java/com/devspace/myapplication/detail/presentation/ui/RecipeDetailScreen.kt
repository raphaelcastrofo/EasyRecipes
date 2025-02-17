package com.devspace.myapplication.detail.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RecipeDetailScreen(navController: String?) {

    Column(
        modifier = Modifier
            .fillMaxWidth()

    ) {
        Text(
            modifier = Modifier.padding(24.dp),
            fontSize = 32.sp,
            text = "Recipes",
            fontWeight = FontWeight.SemiBold
        )

    }
}