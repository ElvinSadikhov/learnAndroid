package com.example.test_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() { // need for 4th and 5th ways! (, View.OnClickListener)
    val MYLOG = "LogMainAct"

    lateinit var myTView: TextView
    lateinit var myButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myTView = findViewById(R.id.myTView)
        myButton = findViewById(R.id.myButton)


        // there are 5 ways of setting Listener

        /*
        // 1st way
        myButton.setOnClickListener {
            myTView.setText("hi")
        }
        */

       /*
       // 2nd way
        myButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                myTView.setText("hi")
            }

        })
        */

        /*
        // 3rd way
        myButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                myTView.setText("hi")
            }

        })
        */

        /*
        // 4th way (use interface)
        myButton.setOnClickListener(this)
        */

        /*
        // 5th way
        myButton.setOnClickListener(listener)
        */

        Log.d(MYLOG,"onCreate")
    }

   /*
   //4th way
    override fun onClick(view: View?) {
        when(view?.id){
            R.id.myButton->{
                myTView.setText("hi")
            }
        }
    }
    */

    /*
    // 5th way
    val listener = View.OnClickListener { view->
        when (view.getId()){
            R.id.myButton -> myTView.setText("hi")
        }
    }
    */

    override fun onStart() {
        super.onStart()
        Log.d(MYLOG,"onStart")
    }
    override fun onResume() {
        super.onResume()
        Log.d(MYLOG,"onResume")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(MYLOG,"onRestart")
    }

    override fun onPause() {
        super.onPause()
        Log.d(MYLOG,"onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(MYLOG,"onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(MYLOG,"onDestroy")
    }
}