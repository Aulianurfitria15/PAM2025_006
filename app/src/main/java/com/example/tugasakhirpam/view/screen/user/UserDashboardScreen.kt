package com.example.tugasakhirpam.view.screen.user

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tugasakhirpam.view.component.AppTopBar
import com.example.tugasakhirpam.view.component.FilmCard
import com.example.tugasakhirpam.viewmodel.FilmViewModel

@Composable
fun UserDashboardScreen(
    viewModel: FilmViewModel,
    onFilmClick: (Int) -> Unit,

    onLogout: () -> Unit
) {
    var keyword by remember { mutableStateOf("") }

    val films by if (keyword.isEmpty()) {
        viewModel.filmList.collectAsState()
    } else {
        viewModel.searchFilm(keyword).collectAsState(initial = emptyList())
    }

    Scaffold(
        topBar = {
            AppTopBar(
                title = "Dashboard User",
                onLogout = onLogout // 🔥 ICON LOGOUT
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {

            // 🔍 SEARCH
            OutlinedTextField(
                value = keyword,
                onValueChange = { keyword = it },
                label = { Text("Cari film") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            // 📄 LIST FILM
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(films) { film ->
                    FilmCard(
                        film = film,
                        onClick = { onFilmClick(film.id) }
                    )
                }
            }
        }
    }
}
