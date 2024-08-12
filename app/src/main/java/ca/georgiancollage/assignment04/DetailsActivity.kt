package ca.georgiancollage.assignment04

import android.R
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import ca.georgiancollage.assignment04.databinding.ActivityDetailsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * DetailsActivity is responsible for managing the detailed view of a specific task.
 * It allows users to view, update, or delete a task, as well as create a new one.
 * This activity utilizes data binding for its UI components and communicates with DataManager to perform database operations.
 */
class DetailsActivity : AppCompatActivity() {

    // View binding object for this activity
    private lateinit var binding: ActivityDetailsBinding

    // DataManager instance for database operations
    private lateinit var dataManager: DataManager

    // Variable to store the ID of the task being viewed or edited
    private var taskShowId: Int? = null

    /**
     * onCreate is called when the activity is first created.
     * It initializes the activity's UI components and sets up the necessary data adapters and event listeners.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down, this contains the data it most recently supplied.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up the spinner for task types
        val taskTypes = arrayOf("Work", "Private")
        val adapter = ArrayAdapter(this, R.layout.simple_spinner_item, taskTypes)
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        binding.spinnerTaskType.adapter = adapter

        // Initialize DataManager
        dataManager = DataManager.instance(this)

        // Retrieve the task ID from the intent extras
        taskShowId = intent.getIntExtra("taskShowId", -1).takeIf { it != -1 }

        // Load the task if an ID is provided, otherwise hide the delete button
        if (taskShowId != null) {
            loadTASKShow(taskShowId!!)
        } else {
            binding.deleteButton.visibility = View.GONE
        }

        // Set up the calendar view listener for date selection
        binding.calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val selectedDate = "$year-${month + 1}-$dayOfMonth"
            binding.textViewDueDate.text = selectedDate
            binding.calendarView.visibility = View.GONE
        }

        // Set up button listeners
        binding.backButton.setOnClickListener {
            finish()
        }

        binding.updateButton.setOnClickListener {
            saveTask()
        }

        binding.deleteButton.setOnClickListener {
            deleteTaskShow()
        }

        binding.cancelButton.setOnClickListener {
            finish()
        }
    }

    /**
     * Loads the details of a task from the database using the provided ID.
     * The retrieved task details are then displayed in the corresponding UI components.
     *
     * @param id The ID of the task to be loaded.
     */
    private fun loadTASKShow(id: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            val taskShow = dataManager.getTaskShowById(id)
            taskShow?.let {
                binding.editTask.setText(it.title)
                binding.editNotes.setText(it.notes)
                binding.textViewDueDate.text = it.dueDate

                // Set the selected task type in the spinner
                val taskTypePosition = (binding.spinnerTaskType.adapter as ArrayAdapter<String>).getPosition(it.taskType)
                binding.spinnerTaskType.setSelection(taskTypePosition)
            }
        }
    }

    /**
     * Saves the task details entered by the user.
     * If the task already exists, it updates the task; otherwise, it creates a new task.
     * Provides feedback to the user upon successful operation.
     */
    private fun saveTask() {
        val title = binding.editTask.text.toString()
        val notes = binding.editNotes.text.toString()
        val dueDate = binding.textViewDueDate.text.toString()
        val dueTime = ""
        val taskType = binding.spinnerTaskType.selectedItem.toString()
        val isCompleted = binding.switchDueDate.isChecked

        if (title.isNotEmpty() && dueDate.isNotEmpty()) {
            val taskShow = TASKShow(
                id = taskShowId ?: 0,
                title = title,
                isCompleted = isCompleted,
                dueDate = dueDate,
                dueTime = dueTime,
                taskType = taskType,
                notes = notes
            )

            CoroutineScope(Dispatchers.Main).launch {
                if (taskShowId == null) {
                    dataManager.insert(taskShow)
                    Toast.makeText(this@DetailsActivity, "Task Added", Toast.LENGTH_SHORT).show()
                } else {
                    dataManager.update(taskShow)
                    Toast.makeText(this@DetailsActivity, "Task Updated", Toast.LENGTH_SHORT).show()
                }
                finish()
            }
        } else {
            Toast.makeText(this, "Please fill in the task title", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Deletes the currently loaded task after confirming the user's intention through a dialog.
     * Provides feedback to the user upon successful deletion.
     */
    private fun deleteTaskShow() {
        taskShowId?.let { id ->
            AlertDialog.Builder(this)
                .setTitle("Delete Task")
                .setMessage("Are you sure you want to delete this task?")
                .setPositiveButton("Yes") { dialog, _ ->
                    CoroutineScope(Dispatchers.Main).launch {
                        val taskShow = dataManager.getTaskShowById(id)
                        taskShow?.let {
                            dataManager.delete(it)
                            Toast.makeText(this@DetailsActivity, "Task Deleted", Toast.LENGTH_SHORT).show()
                            finish()
                        }
                    }
                }
                .setNegativeButton("No", null)
                .show()
        }
    }
}
