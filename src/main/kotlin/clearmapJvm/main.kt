package clearmapJvm

import clearmap.backend.IMasterService
import clearmap.backend.MasterService
import clearmapJvm.implementations.DependenecyInjectionSetup
import sguiSwing.hybrid.EngineLaunchpoint
import sguiSwing.hybrid.MDebug
import javax.swing.SwingUtilities

fun main( args: Array<String>){
    ClearMapJvm().run()
}

class ClearMapJvm {
    private lateinit var _master  : IMasterService

    fun run() {
        val str = "kind;C:\\file\\dir"
        val loc = str.indexOf(';')
        println(str.substring(0,loc))
        try{
            SwingUtilities.invokeAndWait {
                // For reasons that I will never understand, it is very important that nothing references EngineLaunchpoint.gle
                // directly before this line of code which MUST be in the Swing thread.
                EngineLaunchpoint.gle
                DependenecyInjectionSetup.doDI()
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