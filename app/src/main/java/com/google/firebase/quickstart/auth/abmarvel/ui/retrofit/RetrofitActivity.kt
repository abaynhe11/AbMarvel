package com.google.firebase.quickstart.auth.abmarvel.ui.retrofit


import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.quickstart.auth.abmarvel.R
import com.google.firebase.quickstart.auth.abmarvel.data.api.ApiHelperImpl
import com.google.firebase.quickstart.auth.abmarvel.data.api.RetrofitBuilder
import com.google.firebase.quickstart.auth.abmarvel.data.local.DatabaseBuilder
import com.google.firebase.quickstart.auth.abmarvel.data.local.DatabaseHelperImpl
import com.google.firebase.quickstart.auth.abmarvel.data.model.ApiUser
import com.google.firebase.quickstart.auth.abmarvel.ui.base.ApiUserAdapter
import com.google.firebase.quickstart.auth.abmarvel.utils.Status
import com.google.firebase.quickstart.auth.abmarvel.utils.ViewModelFactory

import kotlinx.android.synthetic.main.activity_main.*

class RetrofitActivity : AppCompatActivity() {

    private lateinit var viewModel: RetrofitActivityViewModel
    private lateinit var adapter: ApiUserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUI()
        setupViewModel()
        setupObserver()
    }

    private fun setupUI() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter =
            ApiUserAdapter(
                arrayListOf()
            )
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
    }

    private fun setupObserver() {
        viewModel.getUsers().observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    it.data?.let { users -> renderList(users) }
                    recyclerView.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                }
                Status.ERROR -> {
                    //Handle Error
                    progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun renderList(users: List<ApiUser>) {
        adapter.addData(users)
        adapter.notifyDataSetChanged()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(
                ApiHelperImpl(RetrofitBuilder.apiService),
                DatabaseHelperImpl(DatabaseBuilder.getInstance(applicationContext))
            )
        ).get(RetrofitActivityViewModel::class.java)
    }
}