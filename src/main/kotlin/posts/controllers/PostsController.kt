package posts.controllers

import com.github.kittinunf.result.map
import posts.repositories.PostsRepository
import utilities.Writable
import utilities.FileWriter

class PostsController(private val postsRepository: PostsRepository, private val fileWriter: FileWriter) {
    fun fetchAllAndSaveTo(filePath: String) {
        postsRepository.getAllPosts()
            .map { posts ->
                println("Fetched from remote!")
                fileWriter.writeToFile(filePath,
                    object : Writable {
                        override fun decode(): String {
                            return posts
                        }
                    })
            }
    }
}

