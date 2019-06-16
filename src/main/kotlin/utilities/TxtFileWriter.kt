package utilities

import com.github.kittinunf.result.*
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import mu.KotlinLogging
import java.io.File
private val logger = KotlinLogging.logger {  }
class TxtFileWriter : FileWriter {

    override suspend fun writeToFile(filePath: String, element: Writable): Result<Unit, Exception> {
        return Result.of(File(filePath))
            .map { it.writeText(element.decodeToString()) }
            .run {
                when (this) {
                    is Result.Success -> logger.info { "Successfully saved to: $filePath" }
                    is Result.Failure -> logger.error(error) { "Error during save to file!\nMessage: ${error.message}" }
                }
                this
            }
    }
}