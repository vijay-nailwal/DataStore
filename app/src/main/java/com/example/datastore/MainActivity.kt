package com.example.datastore

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.datastore.databinding.ActivityMainBinding
import com.example.datastore.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding()
        saveToLocal()
        readFromLocal()
    }

    private fun readFromLocal() {
        lifecycleScope.launchWhenStarted {
            mainViewModel.readFromLocal.collect { data ->
                binding.textViewDisplay.text = "Preference Value is : $data"
            }
        }

    }

    private fun dataBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun saveToLocal() {
        binding.apply {
            buttonOk.setOnClickListener {
                if (!TextUtils.isEmpty(editTextName.text.toString())) {
                    Log.d("main", "saveToLocal: ${editTextName.text}")
                    mainViewModel.saveToLocal(name = editTextName.text.toString())
                } else {
                    Toast.makeText(this@MainActivity, "fill the field", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}