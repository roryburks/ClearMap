package clearmap.backend

import clearmap.backend.commands.CentralCommandExecutor
import clearmap.backend.commands.ICentralCommandExecutor
import clearmap.backend.tiles.ITileService
import clearmap.backend.tiles.TileService
import clearmap.backend.tiles.TileServiceProvider
import clearmap.middleware.FrameManagementSvc
import clearmap.middleware.IFrameManagementSvc

interface  IMasterService {
    val commandExecutor: ICentralCommandExecutor
    val frameManager : IFrameManagementSvc

    val tileSvc : ITileService
}

class MasterService : IMasterService{
    override val frameManager: IFrameManagementSvc = FrameManagementSvc(this)
    override val tileSvc: ITileService = TileServiceProvider.svc.value
    override val commandExecutor = CentralCommandExecutor(this)
}