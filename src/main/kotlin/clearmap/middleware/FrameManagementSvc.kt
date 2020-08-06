package clearmap.middleware

import clearmap.backend.IMasterService
import clearmap.frontend.RootWindow
import javax.swing.WindowConstants

interface  IFrameManagementSvc {
    fun start()
}

class FrameManagementSvc(private  val _master : IMasterService)  : IFrameManagementSvc{
    val root by lazy { RootWindow(_master) }

    override fun start() {
        root.pack()
        root.isLocationByPlatform = true
        root.isVisible = true
        root.defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
    }

}
