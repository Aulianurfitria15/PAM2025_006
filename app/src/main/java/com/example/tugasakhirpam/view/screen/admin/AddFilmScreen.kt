package com.example.tugasakhirpam.view.screen.admin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import com.example.tugasakhirpam.viewmodel.FilmViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddFilmScreen(
    viewModel: FilmViewModel,
    filmId: Int? = null,
    onBack: () -> Unit
) {
    var title by remember { mutableStateOf("") }
    var genre by remember { mutableStateOf("") }
    var year by remember { mutableStateOf("") }
    var rating by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    Scaffold(
        containerColor = Color(0xFF4F5F59), // 🔥 background hijau
        topBar = {
            TopAppBar(
                title = { Text("Tambah Film", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Kembali",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF4F5F59) // 🔥 samakan
                )
            )
        }
    )
    { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp) // 🔥 AUTO JARAK
        ) {

            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Judul Film") },
                leadingIcon = {
                    Icon(Icons.Default.Movie, contentDescription = null, tint = Color(0xFF4F5F59))
                },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = Color(0xFF4F5F59)
                ),
                shape = RoundedCornerShape(50)
            )

            OutlinedTextField(
                value = genre,
                onValueChange = { genre = it },
                label = { Text("Genre") },
                leadingIcon = {
                    Icon(
                        Icons.Default.Category,
                        contentDescription = null,
                        tint = Color(0xFF4F5F59)
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = Color(0xFF4F5F59)
                ),
                shape = RoundedCornerShape(50)
            )

            OutlinedTextField(
                value = year,
                onValueChange = { year = it },
                label = { Text("Tahun") },
                leadingIcon = {
                    Icon(
                        Icons.Default.DateRange,
                        contentDescription = null,
                        tint = Color(0xFF4F5F59)
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = Color(0xFF4F5F59)
                ),
                shape = RoundedCornerShape(50)
            )

            OutlinedTextField(
                value = rating,
                onValueChange = { rating = it },
                label = { Text("Rating") },
                leadingIcon = {
                    Icon(Icons.Default.Star, contentDescription = null, tint = Color(0xFF4F5F59))
                },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = Color(0xFF4F5F59)
                ),
                shape = RoundedCornerShape(50)
            )

            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Deskripsi") },
                leadingIcon = {
                    Icon(
                        Icons.Default.Description,
                        contentDescription = null,
                        tint = Color(0xFF4F5F59)
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = Color(0xFF4F5F59)
                ),
                shape = RoundedCornerShape(30)
            )

            Spacer(modifier = Modifier.height(24.dp)) // 🔥 JARAK KE BUTTON

            Button(
                onClick = {
                    val posterUrl =
                        "https://picsum.photos/seed/${title.hashCode()}/300/450"

                    viewModel.insertFilm(
                        Film(
                            title = title,
                            genre = genre,
                            year = year.toInt(),
                            rating = rating.toDouble(),
                            poster = posterUrl,
                            description = description
                        )
                    )
                    onBack()
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFB8484E),   // 🔴 MERAH SESUAI TEMA
                    contentColor = Color.White            // 🔤 warna teks
                ),
                shape = RoundedCornerShape(8.dp)          // opsional biar cakep
            ) {
                Text("SIMPAN")
            }
        }
    }
    }
