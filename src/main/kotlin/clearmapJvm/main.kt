package clearmapJvm

import clearmap.backend.IMasterService
import clearmap.backend.MasterService
import rbJvm.vectrix.SetupVectrixForJvm
import sguiSwing.hybrid.EngineLaunchpoint
import sguiSwing.hybrid.MDebug
import javax.swing.SwingUtilities

fun main( args: Array<String>){
    ClearMapJvm().run()
}

class ClearMapJvm {
    private lateinit var _master  : IMasterService

    fun run() {
        try{
            SwingUtilities.invokeAndWait {
                // For reasons that I will never understand, it is very important that nothing references EngineLaunchpoint.gle
                // directly before this line of code which MUST be in the Swing thread.
                EngineLaunchpoint.gle
                BackendDISetup.doDI()
                _master = MasterService()
                _master.frameManager.start()
            }
        }catch (e: Exception)
        {
            e.printStackTrace()
            MDebug.handleError(MDebug.ErrorType.FATAL, e.message ?: "Root-level exception caught.", e)
        }

    }
}