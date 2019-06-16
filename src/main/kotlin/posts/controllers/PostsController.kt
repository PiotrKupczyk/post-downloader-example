package posts.controllers

import com.github.kittinunf.result.*
import kotlinx.coroutines.*
import posts.repositories.PostsRepository
import utilities.FileWriter

class PostsController(private val postsRepository: PostsRepository, private val fileWriter: FileWriter) {
    fun fetchAllAndSaveTo(fileDirectory: String) {
        postsRepository
            .getAllPosts()
            .map { posts ->
                posts.map { post ->
                    val filePath = "$fileDirectory/post${post.id}.txt"
                    CoroutineScope(Dispatchers.IO).launch { fileWriter.writeToFile(filePath, post) }
                }.forEach { runBlocking { it.join() } }

            }
    }
}


