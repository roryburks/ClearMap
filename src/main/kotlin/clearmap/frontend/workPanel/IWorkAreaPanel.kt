package clearmap.frontend.workPanel

import rb.glow.IGraphicsContext
import sgui.components.IComponent

interface IWorkAreaPanel {
    val component : IComponent
    fun injectDrawingRoutine(lambda: (IGraphicsContext) -> Unit)
}

