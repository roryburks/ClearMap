package clearmap.frontend.workPanel

import clearmap.frontend.workPanel.map.MapWorkController
import clearmap.frontend.workPanel.map.MapWorkControllerProvider
import rb.glow.IGraphicsContext
import rb.owl.IObservable
import rb.owl.addObserver
import rb.owl.observer
import sgui.components.IComponent
import sgui.components.events.MouseEvent

interface IWorkAreaSubController {
    fun draw(gc: IGraphicsContext)
    fun handleMouse(evt: MouseEvent)
    val redrawObs : IObservable<()->Unit>
}

class WorkAreaController(
    private val _mapController : MapWorkController )
{
    var drawComponent: IComponent? = null
    private var _state = State.Map

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

    fun changeState( state: State) {
        if( _state != state) {
            _state = state
            drawK = _subControllerMap[_state]?.redrawObs?.addObserver { drawComponent?.redraw() } }
    }

    private var drawK = _subControllerMap[_state]?.redrawObs?.addObserver {
        drawComponent?.redraw()
    }
}

object WorkAreaControllerProvider {
    val controller = lazy { WorkAreaController(
        MapWorkControllerProvider.controller.value)
    }
}