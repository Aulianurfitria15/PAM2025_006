package com.example.tugasakhirpam.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tugasakhirpam.data.dao.FilmDao
import com.example.tugasakhirpam.data.dao.ReportDao
import com.example.tugasakhirpam.data.dao.UserDao
import com.example.tugasakhirpam.data.model.Film
import com.example.tugasakhirpam.data.model.User

@Database(
    entities = [
        Film::class,
        User::class
    ],
    version = 1,
    exportSchema = false
)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun filmDao(): FilmDao
    abstract fun userDao(): UserDao
    abstract fun reportDao(): ReportDao

    companion object {
        @Volatile
        private var INSTANCE: MovieDatabase? = null

        fun getDatabase(context: Context): MovieDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    MovieDatabase::class.java,
                    "movie_database"
                )
                    .fallbackToDestructiveMigration() // ⬅️ WAJIB
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}
