package com.example.tugasakhirpam.view.screen.user

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.tugasakhirpam.view.component.AppTopBar
import com.example.tugasakhirpam.viewmodel.FilmViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserFilmDetailScreen(
    viewModel: FilmViewModel, // 1. Tambahkan ViewModel
    filmId: Int,              // 2. Gunakan filmId, bukan objek Film
    onBack: () -> Unit        // 3. Tambahkan tombol kembali
) {
    // Ambil data dari ViewModel menggunakan filmId
    val film by viewModel.getFilmById(filmId).collectAsState(initial = null)

    Scaffold(
        topBar = { AppTopBar(title = "Detail Film", onBack = onBack) }
    ) { padding ->
        // Gunakan film?.let untuk menangani kasus saat data masih loading (null)
        film?.let { filmDetail ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Tambahkan poster agar lebih menarik
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(filmDetail.poster)
                        .crossfade(true)
                        .build(),
                    contentDescription = "Poster ${filmDetail.title}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    contentScale = ContentScale.Crop
                )
                Text(filmDetail.title, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Text("Genre: ${filmDetail.genre}")
                Text("Tahun Rilis: ${filmDetail.year}")
                Text("Rating: ${filmDetail.rating}")
                Spacer(modifier = Modifier.height(8.dp))
                Text("Deskripsi:", fontWeight = FontWeight.SemiBold)
                Text(filmDetail.description)
            }
        }
    }
}
