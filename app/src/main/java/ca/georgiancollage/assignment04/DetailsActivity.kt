package ca.georgiancollage.assignment04

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ca.georgiancollage.assignment04.databinding.ActivityDetailsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsActivity :  AppCompatActivity()
{
    private lateinit var binding: ActivityDetailsBinding

    private lateinit var dataManager: DataManager

    private var taskShowId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataManager = DataManager.instance(this)

        taskShowId = intent.getIntExtra("taskShowId", -1).takeIf { it != -1 }

        if (taskShowId != null) {
            loadTASKShow(taskShowId!!)
        } else {
            binding.deleteButton.visibility = View.GONE
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

    private fun loadTASKShow(id: Int)
        {
            CoroutineScope(Dispatchers.Main).launch {
                val taskShow = dataManager.getTaskShowById(id)
                taskShow?.let {
                    binding.editTask.setText(it.title)
                    binding.editNotes.setText(it.notes)
                    binding.textViewDueDate.text = it.dueDate
                    binding.switchCompleted.isChecked = it.isCompleted
                }
            }
        }

    private fun saveTask() {
        val title = binding.editTask.text.toString()
        val notes = binding.editNotes.text.toString()
        val dueDate = binding.textViewDueDate.text.toString()
        val isCompleted = binding.switchCompleted.isChecked
        val dueTime = ""
        val taskType = ""

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
                if (taskShowId == null)
                {
                    dataManager.insert(taskShow)
                    Toast.makeText(this@DetailsActivity, "Task Added", Toast.LENGTH_SHORT).show()
                } else {
                    dataManager.update(taskShow)
                    Toast.makeText(this@DetailsActivity, "Task Updated", Toast.LENGTH_SHORT).show()
                }
                finish()
                }
        } else
        {
            Toast.makeText(this, "Please fill in the task title", Toast.LENGTH_SHORT).show()
        }
    }

    private fun deleteTaskShow() {
        taskShowId?.let { id ->
            AlertDialog.Builder(this)
                .setTitle("Delete Task")
                .setMessage("Are you sure you want to delete this task?")
                .setPositiveButton("Yes") { dialog, which ->
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
