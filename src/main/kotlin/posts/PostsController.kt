package posts

import com.github.kittinunf.result.*
import kotlinx.coroutines.*
import utilities.FileWriter
import java.lang.Exception

class PostsController(private val postsRepository: PostsRepository, private val fileWriter: FileWriter) {

    fun fetchAllPosts(): Result<List<Post>, Exception> = postsRepository.getAllPosts()

    fun saveTo(fileDirectory: String, posts: List<Post>) {
        posts.map { post ->
            val filePath = "$fileDirectory/post${post.id}.txt"
            CoroutineScope(Dispatchers.IO).launch { fileWriter.writeToFile(filePath, post) }
        }.forEach { runBlocking { it.join() } }
    }
}


