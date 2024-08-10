package ca.georgiancollage.assignment04

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
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