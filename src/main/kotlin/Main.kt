import posts.controllers.PostsController
import posts.repositories.PostsRepository
import posts.services.PostsService
import utilities.TxtFileWriter

fun main() {
    val filePath = "src/main/resources/posts.txt"
    val url = "https://jsonplaceholder.typicode.com/posts"
    val postsController = PostsController(
        PostsRepository(
                PostsService(url)
        ),
        TxtFileWriter())

    postsController.fetchAllAndSaveTo(filePath)
}