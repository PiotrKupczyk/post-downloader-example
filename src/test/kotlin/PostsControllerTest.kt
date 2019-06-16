import com.github.kittinunf.result.Result
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import posts.Post
import posts.PostsController
import posts.PostsRepository
import utilities.FileWriter
import java.lang.Exception

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PostsControllerTest {

    private val postsRepository: PostsRepository = mockk()
    private val fileWriter: FileWriter = mockk()
    private val postsController: PostsController = PostsController(postsRepository, fileWriter)

    @BeforeEach
    fun setup() {
        clearAllMocks()
    }

    @Test
    fun `Controller with correct data gets all posts as a list`() {
        every { postsRepository.getAllPosts() } returns Result.of {
            listOf(
                Post(1, 1, "some title", "some body"),
                Post(1, 2, "some title", "some body")
            )
        }
        assertTrue { postsController.fetchAllPosts() is Result.Success }
        assertTrue { postsController.fetchAllPosts().get().isNotEmpty() }
    }

    @Test
    fun `Controller with incorrect data returns fails`() {
        every { postsRepository.getAllPosts() } returns Result.of {
            throw Exception()
        }
        assertTrue { postsController.fetchAllPosts() is Result.Failure }
    }

    }
