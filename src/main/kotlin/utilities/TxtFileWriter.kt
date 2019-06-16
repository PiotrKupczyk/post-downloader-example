package utilities

import com.github.kittinunf.result.*
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import java.io.File

class TxtFileWriter : FileWriter {

    override suspend fun writeToFile(filePath: String, element: Writable): Result<Unit, Exception> {
        return Result.of(File(filePath))
            .map { it.writeText(element.decodeToString()) }
            .run {
                when (this) {
                    is Result.Success -> println("Successfully saved to: $filePath")
                    is Result.Failure -> println("Error during save to file!\nMessage: ${error.message}")
                }
                return@run this
            }
    }
}