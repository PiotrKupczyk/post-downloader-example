import posts.controllers.PostsController
import posts.repositories.PostsRepository
import posts.services.PostsService
import utilities.TxtFileWriter
import kotlin.system.measureTimeMillis

fun main() {
    val fileDirectory = "src/main/kotlin/result"
    val url = "https://jsonplaceholder.typicode.com/posts"
    val postsController = PostsController( PostsRepository( PostsService(url) ), TxtFileWriter() )
    val time = measureTimeMillis {
        postsController.fetchAllAndSaveTo(fileDirectory)
    }
    println(time)
}