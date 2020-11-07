package clearmap.frontend.workPanel.map.behaviors

import clearmap.frontend.workPanel.map.MapWorkBehaviorEngine
import kotlinx.coroutines.processNextEventInCurrentThread
import rb.glow.IGraphicsContext
import sgui.components.events.MouseEvent

abstract class MapBehavior( val engine : MapWorkBehaviorEngine) {
    open fun onStart() {}
    open fun onEnd() {}
    open fun onTick(evt: MouseEvent) {}
    open fun draw(gc: IGraphicsContext) {}

    fun end() {
        engine.setBehavior(null)
    }
}