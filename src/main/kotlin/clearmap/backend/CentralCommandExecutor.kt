package clearmap.backend

import sgui.menus.ICommandExecutor

interface ICentralCommandExecutor : ICommandExecutor

class CentralCommandExecutor : ICentralCommandExecutor{
    override fun executeCommand(command: String, extra: Any?): Boolean {
        return  false
    }

}