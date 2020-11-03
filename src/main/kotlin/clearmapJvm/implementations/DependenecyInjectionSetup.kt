package clearmapJvm.implementations

import clearmap.backend.BackendDI
import clearmapJvm.ClearMapLogger
import clearmapJvm.setupSwGuiStuff
import rb.global.GlobalDependencySet
import rbJvm.glow.JvmGLowDependencyInjection
import rbJvm.vectrix.SetupVectrixForJvm
import sguiSwing.hybrid.EngineLaunchpoint

object DependenecyInjectionSetup {
    fun doDI() {
        SetupVectrixForJvm()
        setupSwGuiStuff()
        GlobalDependencySet.Logger = lazy { ClearMapLogger }
        JvmGLowDependencyInjection.setDi(EngineLaunchpoint.gle)

        BackendDI.configLayer = lazy { JvmConfigLayer(DependenecyInjectionSetup.javaClass) }
        BackendDI.dialog = lazy { SwingDialogue(BackendDI.configLayer.value) }
    }
}