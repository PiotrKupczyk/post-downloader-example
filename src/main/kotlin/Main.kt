import com.github.kittinunf.result.*
import io.github.cdimascio.dotenv.DotEnvException
import io.github.cdimascio.dotenv.Dotenv
import io.github.cdimascio.dotenv.dotenv
import posts.PostsController
import posts.PostsRepository
import posts.PostsService
import utilities.TxtFileWriter

fun main() {

    Result.of<Dotenv, DotEnvException> {
        dotenv {
            directory = "assets"
            filename = ".env"
            ignoreIfMalformed = true
        }
    }.fold({ dotenv ->
        val serviceUrl = dotenv["service_url"].orEmpty()
        val fileDirectory = dotenv["file_directory"].orEmpty()

        val postsController = PostsController(PostsRepository(PostsService(serviceUrl)), TxtFileWriter())
        postsController
            .fetchAllPosts()
            .success { posts -> postsController.saveTo(fileDirectory, posts) }
    }, { exception ->
        println("Couldn't get env variables.${System.lineSeparator()}Message: ${exception.message}")
    })


}
