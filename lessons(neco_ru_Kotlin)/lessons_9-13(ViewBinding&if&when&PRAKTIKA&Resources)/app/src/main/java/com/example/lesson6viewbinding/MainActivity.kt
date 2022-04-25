package com.example.lesson6viewbinding

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.lesson6viewbinding.constants.Codes
import com.example.lesson6viewbinding.constants.Names
import com.example.lesson6viewbinding.constants.Salaries
import com.example.lesson6viewbinding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var mActBinding: ActivityMainBinding // lateinit initialization

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActBinding = ActivityMainBinding.inflate(layoutInflater) // need to use this line before setContentView
        setContentView(mActBinding.root)// we need to pass a root (constraint layout in this case)

        mActBinding.submitionButton.setOnClickListener{
            mActBinding.imPhoto.visibility = View.VISIBLE // it will change to real photo very fast, so don't worry

            val salary = set_salaries(mActBinding.edName.text.toString(), mActBinding)

            when(salary.toInt()){
                -1 -> {
                    mActBinding.tvStatus.text = "incorrect name!"
                    mActBinding.imPhoto.setImageResource(R.drawable.dula)
                }
                -2 -> {
                    mActBinding.tvStatus.text = "incorrect code!"
                    mActBinding.imPhoto.setImageResource(R.drawable.dula)

                }
                else -> mActBinding.tvStatus.text = "here is your ${salary}$!"

            }
        }

    }
}

private fun set_salaries(name: String, binding: ActivityMainBinding): String{
    when(name){
        Names.DIRECTOR -> {
            if(binding.edCode.text.toString()==Codes.DIRECTOR) {
                binding.imPhoto.setImageResource(R.drawable.bob)
                return Salaries.DIRECTOR
            }
            binding.imPhoto.setImageResource(R.drawable.dula)
            return "-2"
        }
        Names.ASSISTANT_MAIN -> {
            if(binding.edCode.text.toString()==Codes.ASSISTANT_MAIN) {
                binding.imPhoto.setImageResource(R.drawable.rauf)
                return Salaries.ASSISTANT_MAIN
            }
            binding.imPhoto.setImageResource(R.drawable.dula)
            return "-2"

        }
        Names.ASSISTANT_SEC -> {
            if(binding.edCode.text.toString()==Codes.ASSISTANT_SEC) {
                binding.imPhoto.setImageResource(R.drawable.elvin)
                return Salaries.ASSISTANT_SEC
            }
            binding.imPhoto.setImageResource(R.drawable.dula)
            return "-2"
        }
        Names.WORKER -> {
            if(binding.edCode.text.toString()==Codes.WORKER) {
                binding.imPhoto.setImageResource(R.drawable.qurban)
                return Salaries.WORKER
            }
            binding.imPhoto.setImageResource(R.drawable.dula)
            return "-2"
        }
        else -> {
            binding.imPhoto.setImageResource(R.drawable.dula)
            return "-1"
        }
    }
}