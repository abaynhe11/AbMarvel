package com.google.firebase.quickstart.auth.abmarvel.data.api
import com.google.firebase.quickstart.auth.abmarvel.data.model.ApiUser
import kotlinx.coroutines.flow.Flow

interface ApiHelper {

    fun getUsers(): Flow<List<ApiUser>>

    fun getMoreUsers(): Flow<List<ApiUser>>

    fun getUsersWithError(): Flow<List<ApiUser>>

}