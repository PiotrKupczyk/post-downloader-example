import com.github.kittinunf.result.Result
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import posts.Post
import utilities.TxtFileWriter

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TxtFileWriterTest {

    val post = mockk<Post>()
    val txtFileWriter = TxtFileWriter()

    @BeforeEach
    fun setup() {
        clearAllMocks()
    }

    @Test
    fun `Writer with incorrect path fails`() {
        val directoryPath = "/some/incorrect/path"
        every { post.decodeToString() } returns "some string"
        assertTrue { runBlocking {
            return@runBlocking txtFileWriter.writeToFile(directoryPath, post) is Result.Failure
        } }

    }

}