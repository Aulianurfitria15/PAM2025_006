package com.example.tugasakhirpam.data.repository

import com.example.tugasakhirpam.data.dao.ReportDao
import com.example.tugasakhirpam.data.model.GenreCount
import com.example.tugasakhirpam.data.model.Report

import kotlinx.coroutines.flow.Flow

class ReportRepository(
    private val reportDao: ReportDao
) {
    fun getTotalFilm(): Flow<Int> =
        reportDao.getTotalFilm()

    fun getMostGenre(): Flow<String?> =
        reportDao.getMostGenre()

    fun getHighestRating(): Flow<Double?> =
        reportDao.getHighestRating()

    fun getTop3Genre(): Flow<List<GenreCount>> =
        reportDao.getTop3Genre()
}
