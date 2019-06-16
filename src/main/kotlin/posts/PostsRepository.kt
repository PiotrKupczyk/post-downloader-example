package posts

import com.github.kittinunf.result.Result
import kotlinx.coroutines.*
import java.lang.Exception

class PostsRepository(private val postsService: PostsService) {
    fun getAllPosts(): Result<List<Post>, Exception> = runBlocking {
        return@runBlocking postsService.fetchPosts()
    }
}