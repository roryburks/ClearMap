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
            SetupVectrixForJvm()
            setupSwGuiStuff()

            SwingUtilities.invokeAndWait {
                EngineLaunchpoint.gle
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