package com.example.tugasakhirpam.view.screen.admin

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.tugasakhirpam.viewmodel.FilmViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilmDetailScreen(
    viewModel: FilmViewModel,
    filmId: Int,
    onBack: () -> Unit,
    onEditClick: (Int) -> Unit,
    onDeleteConfirm: () -> Unit // Fungsi untuk dieksekusi setelah konfirmasi hapus
) {
    // Ambil detail film dari ViewModel
    val film by viewModel.getFilmById(filmId).collectAsState(initial = null)
    var showDeleteDialog by remember { mutableStateOf(false) }

    // Jika state dialog true, tampilkan AlertDialog
    if (showDeleteDialog) {
        DeleteConfirmationDialog(
            onConfirm = {
                // Sembunyikan dialog
                showDeleteDialog = false
                // HANYA jika film tidak null, lakukan delete
                film?.let {
                    viewModel.deleteFilm(it)
                    onDeleteConfirm() // Kembali ke layar sebelumnya
                }
            },
            onDismiss = {
                // Sembunyikan dialog jika pengguna batal
                showDeleteDialog = false
            }
        )
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(film?.title ?: "Detail Film") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Kembali")
                    }
                },
                actions = {
                    // Tombol Edit
                    IconButton(onClick = { onEditClick(filmId) }) {
                        Icon(Icons.Default.Edit, contentDescription = "Edit Film")
                    }
                    // Tombol Hapus
                    IconButton(onClick = {
                        showDeleteDialog = true // Kembali setelah hapus
                    }) {
                        Icon(Icons.Default.Delete, contentDescription = "Hapus Film")
                    }
                }
            )
        }
    ) { padding ->
        film?.let { filmDetail ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
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
                Text("Deskripsi:", fontWeight = FontWeight.SemiBold)
                Text(filmDetail.description)
            }
        }
    }
}
@Composable
private fun DeleteConfirmationDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Konfirmasi Hapus") },
        text = { Text("Apakah Anda yakin ingin menghapus film ini?") },
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text("Hapus")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Batal")
            }
        }
    )
}
