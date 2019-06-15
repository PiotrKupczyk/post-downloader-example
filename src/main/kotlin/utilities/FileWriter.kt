package utilities

interface FileWriter {
    fun writeToFile(filePath: String, element: Writable)
}