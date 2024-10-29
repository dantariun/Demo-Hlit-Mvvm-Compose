package com.dantariun.data

import com.dantariun.database.UserDao
import com.dantariun.model.User
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DefaultUserRepository @Inject constructor(
    private val userDao: UserDao
) : UserRepository {
    override suspend fun insertUser(user: User) {
        userDao.insertUser(user)
    }

    override suspend fun getUserByEmail(email: String): User? {
        return userDao.getUserByEmail(email)
    }

    override suspend fun getAllUsers(): List<User> {
        return userDao.getAllUsers()
    }

//    override suspend fun getAllUsers() = flow {
//        val user = userDao.getAllUsers()
//        if(user.isNotEmpty()){
//            emit(user)
//        }else{
//            emit(emptyList())
//        }
//    }

    override suspend fun deleteUser(email: String) : Int {
       return userDao.deleteUser(email)
    }
}