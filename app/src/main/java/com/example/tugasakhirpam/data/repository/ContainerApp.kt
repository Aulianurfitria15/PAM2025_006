package com.example.tugasakhirpam.data.repository

import android.content.Context
import com.example.tugasakhirpam.data.database.MovieDatabase

class ContainerApp(context: Context) {

    private val database = MovieDatabase.getDatabase(context)

    val userRepository = UserRepository(database.userDao())
    val filmRepository = FilmRepository(database.filmDao())
    val reportRepository = ReportRepository(database.reportDao())
}
