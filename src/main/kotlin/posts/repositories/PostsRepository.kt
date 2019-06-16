package posts.repositories

import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.result.Result
import kotlinx.coroutines.*
import posts.Post
import posts.services.PostsService

class PostsRepository(private val postsService: PostsService) {
    fun getAllPosts(): Result<List<Post>, FuelError> = runBlocking {
        return@runBlocking postsService.fetchPosts()
    }
}