package com.dantariun.data

import com.dantariun.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun insertUser(user: User)

    suspend fun getUserByEmail(email : String) : User?

    suspend fun getAllUsers() : List<User>

    suspend fun deleteUser(email : String) : Int
}