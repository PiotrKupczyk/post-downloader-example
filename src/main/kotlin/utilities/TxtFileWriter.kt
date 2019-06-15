package utilities

import com.github.kittinunf.result.*
import java.io.File

class TxtFileWriter : FileWriter {

    override fun writeToFile(filePath: String, element: Writable) {
        return Result.of(File(filePath).writeText(element.decode()))
            .run {
                when(this) {
                    is Result.Success -> println("Correctly saved to $filePath")
                    is Result.Failure -> println("Error during save to file. Error message: ${this.error.message}")
                }
            }
    }
}