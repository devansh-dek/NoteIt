package com.example.noteit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.widget.Toolbar

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity() {
    lateinit var navController : NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar= findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)


        navController = findNavController(R.id.fragmentContainerView)

        setupActionBarWithNavController(navController)

    }

    override fun onNavigateUp(): Boolean {

        Toast.makeText(applicationContext,"Back butn",Toast.LENGTH_SHORT).show()
        return super.onNavigateUp() || navController.navigateUp()
    }
}