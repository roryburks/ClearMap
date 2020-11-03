package clearmap.backend

import clearmap.backend.commands.CentralCommandExecutor
import clearmap.backend.commands.ICentralCommandExecutor
import clearmap.backend.tiles.ITileService
import clearmap.backend.tiles.TileService
import clearmap.backend.tiles.TileServiceProvider
import clearmap.middleware.FrameManagementSvc
import clearmap.middleware.IFrameManagementSvc

interface  IMasterService {
    val frameManager : IFrameManagementSvc
}

class MasterService : IMasterService{
    override val frameManager: IFrameManagementSvc = FrameManagementSvc(this)
}