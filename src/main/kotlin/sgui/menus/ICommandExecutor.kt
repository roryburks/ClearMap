package sgui.menus


interface ICommandExecutor {
    fun executeCommand( command: String, extra: Any?) : Boolean
}