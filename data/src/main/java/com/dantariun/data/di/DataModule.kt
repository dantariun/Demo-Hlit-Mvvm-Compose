package com.dantariun.data.di

import com.dantariun.data.DefaultUserRepository
import com.dantariun.data.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsUserRepository(
        userRepository: DefaultUserRepository
    ):UserRepository
}