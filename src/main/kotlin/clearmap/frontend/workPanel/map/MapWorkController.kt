package clearmap.frontend.workPanel.map

import clearmap.backend.BackendDI
import clearmap.backend.map.IMapService
import clearmap.frontend.workPanel.IWorkAreaSubController
import rb.glow.Colors
import rb.glow.IGraphicsContext
import sgui.components.events.MouseEvent
import spirite.specialRendering.SpecialDrawerFactory

class MapWorkController(
    private val _mapSvc : IMapService
) : IWorkAreaSubController
{
    override fun draw(gc: IGraphicsContext) {
        SpecialDrawerFactory.makeSpecialDrawer(gc).drawTransparencyBg(0,0, gc.width, gc.height,16, Colors.BLACK, Colors.DARK_GRAY)
    }

    override fun handleMouse(evt: MouseEvent) {
    }

}

object MapWorkControllerProvider {
    val controller = lazy { MapWorkController(BackendDI.mapSvc.value) }
}