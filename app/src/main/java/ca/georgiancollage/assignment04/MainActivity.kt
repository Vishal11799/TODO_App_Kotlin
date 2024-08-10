package ca.georgiancollage.assignment04

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ca.georgiancollage.assignment04.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}

/**
 * File name: MainActivity.kt
 * Author's name: Vishal Chavda
 * StudentID: 200520835
 * Date: 22 july 2024
 * App description: This is the main activity for the Todo App, which sets up the main layout
 *                  and handles window insets for edge-to-edge display.
 * Version information: 1.0
 */