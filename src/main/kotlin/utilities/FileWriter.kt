package utilities

import com.github.kittinunf.result.Result

interface FileWriter {
    suspend fun writeToFile(filePath: String, element: Writable): Result<Unit, Exception>
}