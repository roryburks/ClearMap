package clearmap.backend

import clearmap.backend.commands.CentralCommandExecutorProvider
import clearmap.backend.tiles.ITileService
import clearmap.backend.tiles.TileServiceProvider
import clearmap.middleware.IFrameManagementSvc

object BackendDI {
    lateinit var dialog: Lazy<IDialog>

    lateinit var frameManager : Lazy<IFrameManagementSvc>
    var tileSvc : Lazy<ITileService> = TileServiceProvider.svc
    var commandExecutor = CentralCommandExecutorProvider.Svc
}

