package com.google.firebase.quickstart.auth.abmarvel.data.local
import com.google.firebase.quickstart.auth.abmarvel.data.local.entity.User
import kotlinx.coroutines.flow.Flow

interface DatabaseHelper {

    fun getUsers(): Flow<List<User>>

    fun insertAll(users: List<User>):Flow<Unit>

}