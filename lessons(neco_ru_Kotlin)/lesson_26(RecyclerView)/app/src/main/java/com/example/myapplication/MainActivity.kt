package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var mainActBinding: ActivityMainBinding
    private val adapter = PlantAdapter()
    private val imageIdList = listOf(R.drawable.plant_1,
        R.drawable.plant_2,
        R.drawable.plant_3
    )
    private var index = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainActBinding.root)
        init()
    }

    private fun init(){
        // same as "= with(mainActBinding)"
        mainActBinding.apply{
            rcView.layoutManager = GridLayoutManager(this@MainActivity, 3)
            rcView.adapter = adapter
            bAdd.setOnClickListener{
                if (index>=imageIdList.size) index = 0
                var plant = Plant(imageIdList[index], "Plant $index")
                adapter.addPlant(plant)
                index++
            }
        }
    }

}

