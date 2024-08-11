package ca.georgiancollage.assignment04

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import ca.georgiancollage.assignment04.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var dataManager: DataManager

    private val adapter = TASKShowListAdapter(
        onItemClicked = { taskShow ->
            val intent = Intent(this, DetailsActivity::class.java).apply {
                putExtra("taskShowId", taskShow.id)
            }
            startActivity(intent)
        },
        onEditButtonClicked = { taskShow ->
            val intent = Intent(this, DetailsActivity::class.java).apply {
                putExtra("taskShowId", taskShow.id)
                putExtra("isEditMode", true)  // Pass an extra to indicate edit mode
            }
            startActivity(intent)
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataManager = DataManager.instance(this)

        binding.firstRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.firstRecyclerView.adapter = adapter

        loadTASKShows()

        binding.addTask.setOnClickListener {
            val intent = Intent(this, DetailsActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        loadTASKShows()
    }

    private fun loadTASKShows() {
        lifecycleScope.launch {
            val taskShows = dataManager.getAllTaskShows()
            adapter.submitList(taskShows)
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