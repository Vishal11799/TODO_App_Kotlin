package ca.georgiancollage.assignment04

import android.content.Context

class DataManager private constructor(private val database: AppDatabase)
{
    companion object
    {
        private lateinit var database: AppDatabase

        @Volatile
        private var m_instance: DataManager? = null

        fun instance(context: Context): DataManager
        {
            if(m_instance == null)
            {
                synchronized(this) {
                    if(m_instance == null) {
                        m_instance = DataManager(AppDatabase.getDatabase(context))
                        database = m_instance!!.database
                    }
                }
            }
            return m_instance!!
        }
    }

    suspend fun insert(taskShow: TASKShow) = Companion.database.taskDao().insert(taskShow)

    suspend fun update(taskShow: TASKShow) = Companion.database.taskDao().update(taskShow)

    suspend fun delete(taskShow: TASKShow) = Companion.database.taskDao().delete(taskShow)

    suspend fun getAllTaskShows() = Companion.database.taskDao().getAllTaskShows()

    suspend fun getTaskShowById(id: Int) = Companion.database.taskDao().getTaskShowById(id)
}