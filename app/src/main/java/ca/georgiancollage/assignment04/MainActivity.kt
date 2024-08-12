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

/**
 * MainActivity is the main entry point for the Todo App.
 * It sets up the main layout and handles interactions with the task list, allowing users to view, edit, and add tasks.
 */
class MainActivity : AppCompatActivity() {

    // View binding object for this activity
    private lateinit var binding: ActivityMainBinding

    // DataManager instance for database operations
    private lateinit var dataManager: DataManager

    // Adapter for managing the task list
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
                putExtra("isEditMode", true)
            }
            startActivity(intent)
        }
    )

    /**
     * onCreate is called when the activity is first created.
     * It initializes the activity's UI components, sets up the RecyclerView adapter, and loads the task list.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down, this contains the data it most recently supplied.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataManager = DataManager.instance(this)

        // Set up RecyclerView with a LinearLayoutManager and attach the adapter
        binding.firstRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.firstRecyclerView.adapter = adapter

        // Load the list of tasks
        loadTASKShows()

        // Set up the add task button to launch DetailsActivity
        binding.addTask.setOnClickListener {
            val intent = Intent(this, DetailsActivity::class.java)
            startActivity(intent)
        }
    }

    /**
     * onResume is called when the activity is resumed.
     * It reloads the task list to ensure the displayed data is up to date.
     */
    override fun onResume() {
        super.onResume()
        loadTASKShows()
    }

    /**
     * Loads the list of tasks from the database and submits it to the adapter for display in the RecyclerView.
     */
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
 * Date: 22 July 2024
 * App description: This is the main activity for the Todo App, which sets up the main layout
 *  *                  and handles user interactions for viewing, editing, and adding tasks.
 *  *                  The app utilizes RoomDB for local database storage and management of tasks.
 * Version information: 1.0
 */
