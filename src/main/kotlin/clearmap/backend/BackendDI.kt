package clearmap.backend

import clearmap.backend.commands.CentralCommandExecutorProvider
import clearmap.backend.external.IConfigLayer
import clearmap.backend.external.IDialog
import clearmap.backend.map.IMapService
import clearmap.backend.map.MapServiceProvider
import clearmap.backend.tiles.ITileService
import clearmap.backend.tiles.TileServiceProvider
import clearmap.middleware.IFrameManagementSvc

object BackendDI {
    // External
    lateinit var dialog: Lazy<IDialog>
    lateinit var configLayer: Lazy<IConfigLayer>

    lateinit var frameManager : Lazy<IFrameManagementSvc>
    var tileSvc : Lazy<ITileService> = TileServiceProvider.svc
    var mapSvc : Lazy<IMapService> = MapServiceProvider.Svc
    var commandExecutor = CentralCommandExecutorProvider.Svc
}

