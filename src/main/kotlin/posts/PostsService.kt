package posts

import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.coroutines.awaitObjectResult
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.github.kittinunf.result.flatMap
import mu.KotlinLogging

private val logger = KotlinLogging.logger {  }

class PostsService(private val serviceUrl: String) {
    suspend fun fetchPosts(): Result<List<Post>, Exception> {
        return Result.of<Request, Exception> { serviceUrl.httpGet() }
            .flatMap { request -> request.awaitObjectResult(Post.Deserializer()) }
            .run {
                when (this) {
                    is Result.Success -> logger.info { "Successfully fetched all posts from remote!" }
                    is Result.Failure -> logger.error(error) { "Error during fetch from remote!${System.lineSeparator()}Message: ${error.message}" }
                }
                this
            }
    }
}