package com.example.fetchproject.presentation.view

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fetchproject.databinding.ActivityMainBinding
import com.example.fetchproject.presentation.view.adapter.ItemAdapter
import com.example.fetchproject.presentation.viewmodel.ItemViewModel
import com.example.fetchproject.presentation.viewmodel.ItemViewModelFactory
import com.example.fetchproject.data.repository.ItemRepositoryImpl
import com.example.fetchproject.di.RetrofitInstance
import com.example.fetchproject.domain.usecase.GetItemsUseCase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: ItemViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this,
            ItemViewModelFactory(GetItemsUseCase
                (ItemRepositoryImpl(RetrofitInstance.api))))[ItemViewModel::class.java]

        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.items.observe(this, Observer { items ->
            binding.recyclerView.adapter = ItemAdapter(this, items)
        })

        viewModel.fetchItems()
    }
}