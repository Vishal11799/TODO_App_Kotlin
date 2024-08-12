package ca.georgiancollage.assignment04

import androidx.room.*

@Dao
interface TASKDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(taskShow: TASKShow)
    // updates an existing TVShow in the database
    @Update
    suspend fun update(taskShow: TASKShow)

    // Deletes a TVShow from the database
    @Delete
    suspend fun delete(taskShow: TASKShow)

    // Retrieves all TVShows from the database
    @Query("SELECT * FROM task_name")
    suspend fun getAllTaskShows(): List<TASKShow>

    // Retrieves a TVShow by its ID from the database
    @Query("SELECT * FROM task_name WHERE id = :id")
    suspend fun getTaskShowById(id: Int): TASKShow?
}