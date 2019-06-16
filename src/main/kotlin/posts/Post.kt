package posts

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import utilities.Writable
import java.lang.reflect.Type



data class Post (
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String): Writable {

    override fun decodeToString(): String {
        return GsonBuilder().setPrettyPrinting().create().toJson(this)
    }

    class Deserializer: ResponseDeserializable<List<Post>> {
        override fun deserialize(content: String): List<Post>? {
            return Gson().fromJson(content, object : TypeToken<Collection<Post>>() {}.type)
        }
    }
}