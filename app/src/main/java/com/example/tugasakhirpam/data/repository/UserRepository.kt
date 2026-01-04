package com.example.tugasakhirpam.data.repository

import com.example.tugasakhirpam.data.dao.UserDao
import com.example.tugasakhirpam.data.model.User

class UserRepository(
    private val userDao: UserDao
) {

    suspend fun insertUser(user: User) {
        userDao.insertUser(user)
    }

    suspend fun login(username: String, password: String): User? {
        return userDao.login(username, password)
    }
}
