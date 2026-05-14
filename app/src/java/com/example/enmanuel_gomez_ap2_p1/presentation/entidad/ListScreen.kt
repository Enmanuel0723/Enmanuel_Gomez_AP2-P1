package com.example.enmanuel_gomez_ap2_p1.presentation.entidad

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.enmanuel_gomez_ap2_p1.ui.theme.Enmanuel_Gomez_AP2_P1Theme

@Composable
fun ListScreen(
    onNavigateToEdit: (Int?) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "List Screen")
    }
}

@Preview(showBackground = true)
@Composable
fun ListScreenPreview() {
    Enmanuel_Gomez_AP2_P1Theme {
        ListScreen(onNavigateToEdit = {})
    }
}
