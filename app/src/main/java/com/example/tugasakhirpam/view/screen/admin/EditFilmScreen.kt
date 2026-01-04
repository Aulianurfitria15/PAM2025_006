package com.example.tugasakhirpam.view.screen.admin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults.textFieldColors
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.tugasakhirpam.data.model.Film
import com.example.tugasakhirpam.view.component.AppTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditFilmScreen(
    film: Film?,
    onSave: (Film) -> Unit,
    onBack: () -> Unit
) {
    var title by remember(film) { mutableStateOf(film?.title ?: "") }
    var genre by remember(film) { mutableStateOf(film?.genre ?: "") }
    var year by remember(film) { mutableStateOf(film?.year?.toString() ?: "") }
    var rating by remember(film) { mutableStateOf(film?.rating?.toString() ?: "") }
    var description by remember(film) { mutableStateOf(film?.description ?: "") }
    var poster by remember(film) { mutableStateOf(film?.poster ?: "") }

    Scaffold(
        containerColor = Color(0xFF4F5F59), // 🌿 background hijau
        topBar = {
            AppTopBar(
                title = "Edit Film",
                onBack = onBack
            )
        }
    ) { padding ->
        film?.let { existingFilm ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp) // 🔥 biar rapi
            ) {

                // 🎬 POSTER
                OutlinedTextField(
                    value = poster,
                    onValueChange = { poster = it },
                    leadingIcon = {
                        Icon(Icons.Default.Movie, contentDescription = null)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = textFieldColors(),
                    shape = RoundedCornerShape(50)
                )

                // 🎞 JUDUL
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    leadingIcon = {
                        Icon(Icons.Default.Movie, contentDescription = null)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = textFieldColors(),
                    shape = RoundedCornerShape(50)
                )

                // 🏷 GENRE
                OutlinedTextField(
                    value = genre,
                    onValueChange = { genre = it },
                    leadingIcon = {
                        Icon(Icons.Default.Category, contentDescription = null)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = textFieldColors(),
                    shape = RoundedCornerShape(50)
                )

                // 📅 TAHUN
                OutlinedTextField(
                    value = year,
                    onValueChange = { year = it },
                    leadingIcon = {
                        Icon(Icons.Default.DateRange, contentDescription = null)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = textFieldColors(),
                    shape = RoundedCornerShape(50)
                )

                // ⭐ RATING
                OutlinedTextField(
                    value = rating,
                    onValueChange = { rating = it },
                    leadingIcon = {
                        Icon(Icons.Default.Star, contentDescription = null)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = textFieldColors(),
                    shape = RoundedCornerShape(50)
                )

                // 📝 DESKRIPSI
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    leadingIcon = {
                        Icon(Icons.Default.Description, contentDescription = null)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = textFieldColors(),
                    shape = RoundedCornerShape(24.dp)
                )

                Spacer(modifier = Modifier.height(24.dp))

                // 🔴 BUTTON UPDATE
                Button(
                    onClick = {
                        onSave(
                            Film(
                                id = existingFilm.id, // 🔥 PENTING
                                title = title,
                                genre = genre,
                                year = year.toInt(),
                                rating = rating.toDouble(),
                                description = description,
                                poster = poster
                            )
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFB8484E),
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("UPDATE")
                }
            }
        }
    }
}
