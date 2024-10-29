package com.dantariun.domain

import com.dantariun.data.UserRepository
import com.dantariun.model.InvalidUserException
import com.dantariun.model.User
import javax.inject.Inject
import kotlin.jvm.Throws

class LoginUsecase @Inject constructor(
    private val repository: UserRepository
) {

    @Throws(InvalidUserException::class)
    suspend operator fun invoke(user:User) {
        repository.getUserByEmail(user.email)?.let { userByEmail ->
            if(userByEmail.password != user.password)
                throw InvalidUserException("Invalid password")
        } ?: throw InvalidUserException("Invalid email")
    }
}