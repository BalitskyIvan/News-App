package balitsky.newsapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import balitsky.newsapp.data.db.model.NewsDBO

@Dao
interface NewsDao {

    @Query("SELECT * FROM NewsDBO")
    fun getAll(): List<NewsDBO>

    @Insert
    fun saveAll(news: List<NewsDBO>)
}
