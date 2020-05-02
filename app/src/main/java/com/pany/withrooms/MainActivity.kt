package com.pany.withrooms

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pany.withrooms.adapters.DeveloperListAdapter
import com.pany.withrooms.viewmodels.DeveloperViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var developerViewModel: DeveloperViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = DeveloperListAdapter(this)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        developerViewModel = ViewModelProvider(this).get(DeveloperViewModel::class.java)

        developerViewModel.allDevelopers.observe(this, Observer { developers ->
            developers?.let { adapter.setDevelopers(it) }
        })
    }
}
