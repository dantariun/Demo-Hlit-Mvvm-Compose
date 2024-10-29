package com.dantariun.domain

import com.dantariun.data.UserRepository
import com.dantariun.model.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AdminUseCase @Inject constructor(
    private val repository: UserRepository
){
    suspend operator fun invoke() : List<User> {
        return repository.getAllUsers()
    }
}