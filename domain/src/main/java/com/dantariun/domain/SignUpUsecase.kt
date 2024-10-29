package com.dantariun.domain

import android.text.TextUtils
import android.util.Patterns
import com.dantariun.data.UserRepository
import com.dantariun.model.InvalidUserException
import com.dantariun.model.User
import javax.inject.Inject
import kotlin.jvm.Throws

class SignUpUsecase @Inject constructor(
    private val repository: UserRepository
) {

    @Throws(InvalidUserException::class)
    suspend operator fun invoke(user:User) {
        if (repository.getUserByEmail(user.email) != null)
            throw InvalidUserException("Email already exists")
        else {
            isUserInformationValid(user).let { message ->
                if(message != "Success") throw InvalidUserException(message)
                repository.insertUser(user)
            }
        }
    }

    private fun isUserInformationValid(user: User) : String =
        when {
            TextUtils.isEmpty(user.email) -> {
                "Email cannot be empty"
            }
            TextUtils.isEmpty(user.password) -> {
                "Password cannot be empty"
            }
            !Patterns.EMAIL_ADDRESS.matcher(user.email).matches() -> {
                "Invalid email"
            }
            user.password.length <= 6 -> {
                "The password must have a minimum length of 6."
            }
            else -> "Success"
        }
}