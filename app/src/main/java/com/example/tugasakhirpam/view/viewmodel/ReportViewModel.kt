package com.example.tugasakhirpam.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tugasakhirpam.data.repository.ReportRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class ReportViewModel(
    private val repository: ReportRepository
) : ViewModel() {

    val totalFilm = repository.getTotalFilm()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            0
        )

    val mostGenre = repository.getMostGenre()
        .map { it ?: "-" } // 🔥 WAJIB
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            "-"
        )

    val highestRating = repository.getHighestRating()
        .map { it ?: 0.0 } // 🔥 WAJIB
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            0.0
        )

    val top3Genre = repository.getTop3Genre()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            emptyList()
        )
}
