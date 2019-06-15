package posts.services

import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.coroutines.awaitStringResult
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result

class PostsService(private val serviceUrl: String) {
    suspend fun fetchPosts(): Result<String, FuelError> {
        return serviceUrl.httpGet()
            .awaitStringResult()
    }
}