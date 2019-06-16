import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import posts.PostsService
import com.github.kittinunf.result.*
import org.junit.jupiter.api.Assertions.assertTrue

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PostsServiceTest {

    @Test
    fun `Service with correct url should return all posts as a list`() {
        val correctUrl = "https://jsonplaceholder.typicode.com/posts"
        val postsService = PostsService(correctUrl)

        assertTrue(runBlocking { postsService.fetchPosts() is Result.Success } )
    }

    @Test
    fun `Service with wrong url should fail`() {
        val correctUrl = "https://jsonplaceholder.typicode.com/posts1234566/asdas"
        val postsService = PostsService(correctUrl)

        assertTrue { runBlocking { return@runBlocking postsService.fetchPosts() is Result.Failure } }
    }

    @Test
    fun `Service without url's protocol should fail`() {
        val correctUrl = "Not http format url"
        val postsService = PostsService(correctUrl)

        assertTrue { runBlocking { return@runBlocking postsService.fetchPosts() is Result.Failure } }
    }

    @Test
    fun `Service with empty url should fail`() {
        val correctUrl = ""
        val postsService = PostsService(correctUrl)

        assertTrue { runBlocking { return@runBlocking postsService.fetchPosts() is Result.Failure } }
    }

}