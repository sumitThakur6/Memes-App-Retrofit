package com.example.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofit.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var mAdapter : MemeAdapter
    private lateinit var viewModel: MemeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MemeViewModel::class.java]
        binding.rv.layoutManager = LinearLayoutManager(this)
        mAdapter = MemeAdapter(this)
        binding.rv.adapter = mAdapter

        viewModel.memes.observe(this, Observer {
            mAdapter.updateMeme(it)
        })
    }
}