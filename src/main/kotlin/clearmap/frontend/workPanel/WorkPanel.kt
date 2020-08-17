package clearmap.frontend.workPanel

import rb.glow.GraphicsContext
import sgui.components.IComponent

interface IWorkArea {
    val component : IComponent
    fun injectDrawingRoutine(lambda: (GraphicsContext) -> Unit)
}

class WorkPanel(val _area : IWorkArea) : IComponent by _area.component
{

}