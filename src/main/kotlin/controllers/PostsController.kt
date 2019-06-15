package controllers

import com.github.kittinunf.fuel.core.HttpException
import com.github.kittinunf.result.map
import repositories.PostsRepository

class PostsController(private val postsRepository: PostsRepository) {
    fun fetchAndSavePosts(filePath: String) {

    }
}