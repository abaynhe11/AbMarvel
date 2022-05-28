package com.google.firebase.quickstart.auth.abmarvel.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.quickstart.auth.abmarvel.data.api.ApiHelper
import com.google.firebase.quickstart.auth.abmarvel.data.local.DatabaseHelper
import com.google.firebase.quickstart.auth.abmarvel.ui.retrofit.RetrofitActivity
import com.google.firebase.quickstart.auth.abmarvel.ui.retrofit.RetrofitActivityViewModel
import com.google.firebase.quickstart.auth.abmarvel.ui.room.RoomDBViewModel

class ViewModelFactory(private val apiHelper: ApiHelper, private val dbHelper: DatabaseHelper) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RetrofitActivityViewModel::class.java)) {
            return RetrofitActivityViewModel(apiHelper, dbHelper) as T
        }

        if (modelClass.isAssignableFrom(RoomDBViewModel::class.java)) {
            return RoomDBViewModel(apiHelper, dbHelper) as T
        }

        throw IllegalArgumentException("Unknown class name")
    }

}