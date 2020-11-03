package clearmap.backend.commands

import clearmap.backend.BackendDI
import clearmap.backend.external.IDialog
import clearmap.backend.IMasterService
import clearmap.backend.tiles.ITileService
import sgui.menus.ICommand
import sgui.menus.KeyCommand

private abstract class Command  : ICommand<IMasterService> {
    abstract val command: String
    abstract fun execute(svc : ITileService, dialog: IDialog) : Boolean

    init {
        _tileSetCommands[command] = this
    }

    override val commandString: String get() = "tile.$command"
    override val keyCommand: KeyCommand<IMasterService> get() = KeyCommand(commandString)
}

private val _tileSetCommands = mutableMapOf<String, Command>()

class TileSetCommandExecutor(
    private val _tileSvc: ITileService
): ISubCommandExecutor
{
    override fun executeCommand(command: String, extra: Any?): Boolean {
        return _tileSetCommands[command]?.execute(_tileSvc, BackendDI.dialog.value) ?: false
    }

    override val domain: String get() = "tile"

}

object TileSetCommands {
    val LoadTile :ICommand<IMasterService> = object : Command() {
        override val command: String get() = "load-tile"

        override fun execute(svc: ITileService, dialog: IDialog): Boolean {
            val file = dialog.pickFile("tile") ?: return false
            svc.loadTileSet(file, true)
            return true
        }

    }
}