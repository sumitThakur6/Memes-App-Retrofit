package com.example.retrofit

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MemeViewModel(application : Application) : AndroidViewModel(application) {


    private val repository : MemeRepository

    init{
        val dao = MemeDatabase.getDatabase(application).memeDao()
        repository = MemeRepository(dao, application)
        viewModelScope.launch {
            repository.getMeme()
        }
    }
    val memes : LiveData<List<Memes>> = repository.memes
}