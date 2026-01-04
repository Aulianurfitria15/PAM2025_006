package com.example.tugasakhirpam.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.tugasakhirpam.data.model.GenreCount
import com.example.tugasakhirpam.data.model.Report

import kotlinx.coroutines.flow.Flow

@Dao
interface ReportDao {

    @Query("SELECT COUNT(*) FROM film")
    fun getTotalFilm(): Flow<Int>

    @Query("""
        SELECT genre FROM film
        GROUP BY genre
        ORDER BY COUNT(*) DESC
        LIMIT 1
    """)
    fun getMostGenre(): Flow<String?>

    @Query("SELECT MAX(rating) FROM film")
    fun getHighestRating(): Flow<Double?>

    @Query("""
    SELECT genre, COUNT(*) as total
    FROM film
    GROUP BY genre
    ORDER BY total DESC
    LIMIT 3
    """)
    fun getTop3Genre(): Flow<List<GenreCount>>

}
