package com.dantariun.domain

import com.dantariun.data.UserRepository
import javax.inject.Inject

class AdminDeleteUseCase @Inject constructor(
    private val repository: UserRepository
){
    suspend operator fun invoke(email: String) : Int {
        return repository.deleteUser(email)
    }
}