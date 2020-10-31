package clearmap.backend.commands

interface ISubCommandExecutor {
    fun executeCommand( command: String, extra: Any?) : Boolean
    val domain: String
}