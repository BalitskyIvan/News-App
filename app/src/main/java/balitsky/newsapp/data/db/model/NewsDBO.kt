package balitsky.newsapp.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NewsDBO(
    @PrimaryKey
    val id: String,
    val title: String,
    val description: String,
    val content: String,
    val urlToImage: String,
    val publishedAt: String
)
