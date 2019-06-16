package posts

import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.coroutines.awaitObjectResult
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.github.kittinunf.result.flatMap

class PostsService(private val serviceUrl: String) {
    suspend fun fetchPosts(): Result<List<Post>, Exception> {
        return Result.of<Request, Exception> { serviceUrl.httpGet() }
            .flatMap { request -> request.awaitObjectResult(Post.Deserializer()) }
            .run {
                when (this) {
                    is Result.Success -> println("Successfully fetched all posts from remote!")
                    is Result.Failure -> println("Error during fetch from remote!${System.lineSeparator()}Message: ${error.message}")
                }
                return@run this
            }

    }
}