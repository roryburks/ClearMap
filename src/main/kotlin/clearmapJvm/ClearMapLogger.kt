package clearmapJvm

import rb.global.ILogger

object ClearMapLogger  : ILogger {
    override fun logTrace(message: String) {
        println("[T] $message")

    }

    override fun logInformation(message: String) {
        println("[I] $message")
    }

    override fun logWarning(message: String, exception: Exception?) {
        println("[W] $message")
    }

    override fun logError(message: String, exception: Exception?) {
        println("[E] $message")
    }

}