package posts.services

import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.coroutines.awaitObjectResult
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import posts.Post

class PostsService(private val serviceUrl: String) {
    suspend fun fetchPosts(): Result<List<Post>, FuelError> {
        return serviceUrl.httpGet()
            .awaitObjectResult(Post.Deserializer())
            .run {
                when (this) {
                    is Result.Success -> println("Successfully fetched all posts from remote!")
                    is Result.Failure -> println("Error during fetch from remote!\nMessage: ${error.exception.message}")
                }
                return@run this
            }
    }
}