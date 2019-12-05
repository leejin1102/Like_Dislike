package com.example.like_dislike


import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    //Module-level variables
    //var countLike: Int = 0
    lateinit var  counterViewModel: CounterViewModel
    //var countDislike: Int = 0
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("MainActivity", "onCreate")

        //initialise the counter viewmodel
        counterViewModel = ViewModelProviders.of(this).get(CounterViewModel::class.java)

        //initialize the
        sharedPreferences = getPreferences(Context.MODE_PRIVATE)


        imageViewLike.setOnClickListener {
            counterViewModel.countLike++
            textViewLike.text = counterViewModel.countLike.toString()
        }
        imageViewDislike.setOnClickListener {
            counterViewModel.countDislike++
            textViewDislike.text = counterViewModel.countDislike.toString()
        }
    }

    override fun onStart() {
        Log.d("MainActivity", "onStart")
        super.onStart()
    }

    override fun onResume() {
        Log.d("MainActivity", "onResume")

        //Retreive Shared Preference value(s)
        counterViewModel.countLike = sharedPreferences.getInt(getString(R.string.like), 0)

        textViewLike.text = counterViewModel.countLike.toString()
        textViewDislike.text = counterViewModel.countDislike.toString()
        super.onResume()
    }

    override fun onPause() {
        Log.d("MainActivity", "onPause")
        with(sharedPreferences.edit()){
            putInt(getString(R.string.like), counterViewModel.countLike)
            commit()
        }
        super.onPause()
    }

    override fun onStop() {
        Log.d("MainActivity", "onStop")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d("MainActivity", "onDestroy")
        super.onDestroy()
    }
}
