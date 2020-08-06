package sgui.menus

interface ICommand<T> {
    val commandString : String
    val keyCommand: KeyCommand<T>
}
