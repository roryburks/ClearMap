package clearmap.backend.commands

import clearmap.backend.IMasterService
import clearmap.backend.tiles.ITileService
import clearmap.backend.tiles.TileServiceProvider
import sgui.menus.ICommandExecutor

interface ICentralCommandExecutor : ICommandExecutor

class CentralCommandExecutor(
    tileSvc: ITileService
) : ICentralCommandExecutor {
    val subs :Map<String,ISubCommandExecutor>

    init {
        val ces = listOf<ISubCommandExecutor>(
            TileSetCommandExecutor(tileSvc)
        )
        subs = ces
            .map { Pair(it.domain, it )}
            .toMap()
    }


    override fun executeCommand(command: String, extra: Any?): Boolean {
        val dot = command.indexOfFirst { it == '.' }
        if( dot < 0) return false
        val domain = command.substring(0, dot)
        val sub = command.substring(dot+1)
        return subs[domain]?.executeCommand(sub,extra) ?: false
    }
}

object CentralCommandExecutorProvider {
    val Svc : Lazy<ICentralCommandExecutor> = lazy { CentralCommandExecutor(TileServiceProvider.svc.value)  }
}