package com.example.tugasakhirpam.view.screen.admin

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.graphics.Color
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
        containerColor = Color(0xFF4F5F59), // 🔥 background hijau tema
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        film?.title ?: "Detail Film",
                        color = Color.White
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Kembali",
                            tint = Color.White
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { onEditClick(filmId) }) {
                        Icon(
                            Icons.Default.Edit,
                            contentDescription = "Edit Film",
                            tint = Color(0xFFB8484E) // merah
                        )
                    }
                    IconButton(onClick = { showDeleteDialog = true }) {
                        Icon(
                            Icons.Default.Delete,
                            contentDescription = "Hapus Film",
                            tint = Color(0xFFB8484E) // merah
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF4F5F59) // 🔥 hijau
                )
            )
        }
    )
    { padding ->
        film?.let { filmDetail ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp)
                    .fillMaxSize()
            ) {

                // 🖼️ POSTER
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(filmDetail.poster)
                        .crossfade(true)
                        .build(),
                    contentDescription = filmDetail.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(260.dp)
                        .padding(bottom = 12.dp),
                    contentScale = ContentScale.Crop
                )

                // 📦 CARD PUTIH (SAMA KAYA USER)
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(6.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {

                        // 🔝 JUDUL + RATING
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = filmDetail.title,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Surface(
                                color = Color(0xFFFFC107),
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Text(
                                    text = "⭐ ${filmDetail.rating}",
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 12.sp
                                )
                            }
                        }

                        Text(
                            text = "${filmDetail.genre} • ${filmDetail.year}",
                            color = Color.Gray,
                            fontSize = 14.sp
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Deskripsi",
                            fontWeight = FontWeight.SemiBold
                        )

                        Text(
                            text = filmDetail.description,
                            fontSize = 14.sp,
                            color = Color.DarkGray
                        )
                    }
                }
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
