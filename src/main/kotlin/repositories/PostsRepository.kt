package repositories

import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.result.Result
import kotlinx.coroutines.*
import services.PostsService

class PostsRepository(private val postsService: PostsService) {
    fun getAllPosts(): Result<String, FuelError> = runBlocking {
        return@runBlocking postsService.fetchPosts()
    }
}