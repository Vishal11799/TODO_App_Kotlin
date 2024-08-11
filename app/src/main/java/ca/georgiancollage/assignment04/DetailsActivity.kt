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

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    private lateinit var dataManager: DataManager

    private var taskShowId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val taskTypes = arrayOf("Work", "Private")
        val adapter = ArrayAdapter(this, R.layout.simple_spinner_item, taskTypes)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerTaskType.adapter = adapter

        dataManager = DataManager.instance(this)

        taskShowId = intent.getIntExtra("taskShowId", -1).takeIf { it != -1 }

        if (taskShowId != null) {
            loadTASKShow(taskShowId!!)
        } else {
            binding.deleteButton.visibility = View.GONE
        }

        // CalendarView on date change listener
        binding.calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val selectedDate = "$year-${month + 1}-$dayOfMonth"
            binding.textViewDueDate.text = selectedDate
            binding.calendarView.visibility = View.GONE // Hide the calendar after selecting a date
        }

        binding.backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
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

    private fun loadTASKShow(id: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            val taskShow = dataManager.getTaskShowById(id)
            taskShow?.let {
                binding.editTask.setText(it.title)
                binding.editNotes.setText(it.notes)
                binding.textViewDueDate.text = it.dueDate

                // Set the spinner selection based on the task type
                val taskTypePosition = (binding.spinnerTaskType.adapter as ArrayAdapter<String>).getPosition(it.taskType)
                binding.spinnerTaskType.setSelection(taskTypePosition)
            }
        }
    }

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
