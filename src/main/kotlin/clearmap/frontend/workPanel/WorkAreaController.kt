package clearmap.frontend.workPanel

import clearmap.frontend.workPanel.map.MapWorkController
import clearmap.frontend.workPanel.map.MapWorkControllerProvider
import rb.glow.IGraphicsContext
import sgui.components.events.MouseEvent

interface IWorkAreaSubController {
    fun draw(gc: IGraphicsContext)
    fun handleMouse(evt: MouseEvent)
}

class WorkAreaController(
    private val _mapController : MapWorkController )
{
    private val _state = State.Map

    enum class State {
        Map
    }

    private val _subControllerMap = mapOf<State,IWorkAreaSubController>(
        State.Map to _mapController
    )

    fun draw( gc: IGraphicsContext) {
        _subControllerMap[_state]?.draw(gc)
    }

    fun handleMouse( evt: MouseEvent) {
        _subControllerMap[_state]?.handleMouse(evt)
    }
}

object WorkAreaControllerProvider {
    val controller = lazy { WorkAreaController(
        MapWorkControllerProvider.controller.value)
    }
}