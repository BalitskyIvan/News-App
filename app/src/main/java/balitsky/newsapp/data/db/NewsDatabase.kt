package balitsky.newsapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import balitsky.newsapp.data.model.NewsDBO

@Database(entities = [NewsDBO::class], version = 1, exportSchema = false)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
}
