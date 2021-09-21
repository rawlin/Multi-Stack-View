package com.rawlin.bottomsheet

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rawlin.bottomsheet.adapter.EMIAdapter
import com.rawlin.bottomsheet.databinding.ActivityMainBinding
import com.rawlin.bottomsheet.models.EMICard

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val emiAdapter = EMIAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val listOfEMIs = listOf<EMICard>(
            EMICard(1, color1, 4247, 12, true),
            EMICard(2, color2, 5580, 9, false),
            EMICard(3, color3, 8239, 6, false)
        )

        setupRecyclerView(listOfEMIs)


    }

    private fun setupRecyclerView(list: List<EMICard>) {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.apply {
            this.adapter = emiAdapter
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
        }
        emiAdapter.submitList(list)

    }


    companion object {
        private val color1 = Color.parseColor("#48343C")
        private val color2 = Color.parseColor("#807494")
        private val color3 = Color.parseColor("#5C6B8B")
    }
}