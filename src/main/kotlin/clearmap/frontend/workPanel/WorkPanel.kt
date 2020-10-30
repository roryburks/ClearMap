package clearmap.frontend.workPanel

import rb.glow.IGraphicsContext
import sgui.components.IComponent

interface IWorkArea {
    val component : IComponent
    fun injectDrawingRoutine(lambda: (IGraphicsContext) -> Unit)
}

class WorkPanel(val _area : IWorkArea) : IComponent by _area.component
{

}