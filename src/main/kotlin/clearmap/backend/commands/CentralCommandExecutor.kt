package clearmap.backend.commands

import clearmap.backend.IMasterService
import sgui.menus.ICommandExecutor

interface ICentralCommandExecutor : ICommandExecutor

class CentralCommandExecutor(master:IMasterService) : ICentralCommandExecutor {
    val subs :Map<String,ISubCommandExecutor>

    init {
        val ces = listOf<ISubCommandExecutor>(
            TileSetCommandExecutor(master.tileSvc)
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

