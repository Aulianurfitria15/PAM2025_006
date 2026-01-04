package com.example.tugasakhirpam.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "film")
data class Film(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val genre: String,
    val rating: Double,
    val description: String,
    val year: Int,
    val poster: String
)
