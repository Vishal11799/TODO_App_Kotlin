package ca.georgiancollage.assignment04

import android.content.Context

/**
 * DataManager is a singleton class responsible for managing data operations.
 * It acts as a bridge between the UI and the database, providing methods to insert, update, delete,
 * and retrieve TASKShow entities from the database.
 *
 * @param database The AppDatabase instance used to perform database operations.
 */
class DataManager private constructor(private val database: AppDatabase) {

    companion object {
        private lateinit var database: AppDatabase

        @Volatile
        private var m_instance: DataManager? = null

        /**
         * Retrieves the singleton instance of DataManager.
         * If the instance is null, a new one is created, initializing the database instance.
         * The synchronized block ensures that only one instance of DataManager is created at a time.
         *
         * @param context Application context used to get the AppDatabase instance.
         * @return Singleton instance of DataManager.
         */
        fun instance(context: Context): DataManager {
            if (m_instance == null) {
                synchronized(this) {
                    if (m_instance == null) {
                        m_instance = DataManager(AppDatabase.getDatabase(context))
                        database = m_instance!!.database
                    }
                }
            }
            return m_instance!!
        }
    }

    /**
     * Inserts a TASKShow entity into the database.
     *
     * @param taskShow The TASKShow entity to be inserted.
     */
    suspend fun insert(taskShow: TASKShow) = Companion.database.taskDao().insert(taskShow)

    /**
     * Updates an existing TASKShow entity in the database.
     *
     * @param taskShow The TASKShow entity to be updated.
     */
    suspend fun update(taskShow: TASKShow) = Companion.database.taskDao().update(taskShow)

    /**
     * Deletes a TASKShow entity from the database.
     *
     * @param taskShow The TASKShow entity to be deleted.
     */
    suspend fun delete(taskShow: TASKShow) = Companion.database.taskDao().delete(taskShow)

    /**
     * Retrieves all TASKShow entities from the database.
     *
     * @return A list of all TASKShow entities.
     */
    suspend fun getAllTaskShows() = Companion.database.taskDao().getAllTaskShows()

    /**
     * Retrieves a TASKShow entity by its ID.
     *
     * @param id The ID of the TASKShow entity to be retrieved.
     * @return The TASKShow entity with the specified ID.
     */
    suspend fun getTaskShowById(id: Int) = Companion.database.taskDao().getTaskShowById(id)
}
