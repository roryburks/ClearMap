package clearmap.backend

import clearmap.middleware.FrameManagementSvc
import clearmap.middleware.IFrameManagementSvc

interface  IMasterService {
    val commandExecutor: ICentralCommandExecutor
    val frameManager : IFrameManagementSvc

}

class MasterService : IMasterService{
    override val commandExecutor = CentralCommandExecutor()
    override val frameManager: IFrameManagementSvc = FrameManagementSvc(this)
}