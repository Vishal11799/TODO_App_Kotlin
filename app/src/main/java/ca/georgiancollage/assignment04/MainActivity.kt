package ca.georgiancollage.assignment04

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import ca.georgiancollage.assignment04.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
            //array of the mock data
//        val tasks = arrayOf(
//            TASKShow("Finish Project Report", true, "2024-08-12", "14:00", "Work"),
//            TASKShow("Grocery Shopping", false, "2024-08-10", "18:00", "Personal"),
//            TASKShow("Doctor's Appointment", false, "2024-08-11", "09:30", "Health"),
//            TASKShow("Team Meeting", true, "2024-08-09", "10:00", "Work"),
//            TASKShow("Workout Session", false, "2024-08-10", "07:00", "Fitness")
//        )
//            //instance  of firest adptor
//        val firstAdapter = FirstAdapter(tasks)
//// Use view binding to replace findViewById or synthetic properties
//        binding.firstRecyclerView.apply {
//            layoutManager = LinearLayoutManager(context)
//            adapter = firstAdapter
//        }

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