package com.example.lessons_13_24qualifiers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.lessons_13_24qualifiers.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var mActBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mActBinding.root)

    }
}