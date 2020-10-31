package clearmapJvm

import rbJvm.glow.JvmGLowDependencyInjection
import rbJvm.vectrix.SetupVectrixForJvm
import sguiSwing.hybrid.EngineLaunchpoint

object BackendDISetup {
    fun doDI() {
        SetupVectrixForJvm()
        setupSwGuiStuff()
        JvmGLowDependencyInjection.setDi(EngineLaunchpoint.gle)
    }
}