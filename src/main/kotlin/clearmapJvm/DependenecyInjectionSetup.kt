package clearmapJvm

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
    }
}