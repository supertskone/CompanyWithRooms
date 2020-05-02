package com.pany.withrooms.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.pany.withrooms.data.Developer
import com.pany.withrooms.data.DeveloperRepository
import com.pany.withrooms.data.DeveloperRoomDatabase

class DeveloperViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: DeveloperRepository
    val allDevelopers: LiveData<List<Developer>>

    init {
        val wordsDao = DeveloperRoomDatabase.getDatabase(application, viewModelScope).developerDao()
        repository = DeveloperRepository(wordsDao)
        allDevelopers = repository.allDevelopers
    }
}
