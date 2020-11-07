package clearmap.frontend.workPanel.map

import clearmap.frontend.workPanel.map.behaviors.MapBehavior
import rb.glow.IGraphicsContext
import sgui.components.events.MouseEvent

interface IMapWorkBehaviorEngine {
    fun clearBehavior()
    fun tickBehavior(evt: MouseEvent)
    fun drawBehavior(gc: IGraphicsContext)
}


class MapWorkBehaviorEngine : IMapWorkBehaviorEngine{
    private var behavior : MapBehavior? = null

    var prevX: Int = 0; private set
    var prevY: Int = 0; private  set
    var x: Int = 0 ; private set
    var y: Int = 0 ; private set

    override fun clearBehavior() { setBehavior(null) }
    fun setBehavior(behavior: MapBehavior?) {
        this.behavior?.onEnd()
        this.behavior = behavior
        this.behavior?.onStart()
    }

    override fun tickBehavior(evt: MouseEvent) {
        prevX = x
        prevY = y
        x = evt.point.x
        y = evt.point.y

        val cBehavior = behavior
        if( cBehavior != null) {
            cBehavior.onTick(evt)
        }
        else {
            if( evt.type == MouseEvent.MouseEventType.PRESSED) {

            }
        }
    }


    override fun drawBehavior(gc: IGraphicsContext) { behavior?.draw(gc) }


}