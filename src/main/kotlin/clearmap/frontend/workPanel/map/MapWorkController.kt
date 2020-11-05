package clearmap.frontend.workPanel.map

import clearmap.backend.BackendDI
import clearmap.backend.map.IMapService
import clearmap.backend.map.PlacedTileK
import clearmap.backend.tiles.ITileService
import clearmap.frontend.workPanel.IWorkAreaSubController
import rb.glow.Colors
import rb.glow.IGraphicsContext
import rb.owl.IObservable
import rb.owl.Observable
import rb.vectrix.mathUtil.d
import sgui.components.events.MouseEvent
import spirite.specialRendering.SpecialDrawerFactory

class MapWorkController(
    private val _mapSvc : IMapService,
    private val _tileSvc : ITileService
) : IWorkAreaSubController
{
    override fun draw(gc: IGraphicsContext) {
        SpecialDrawerFactory.makeSpecialDrawer(gc).drawTransparencyBg(0,0, gc.width, gc.height,16, Colors.BLACK, Colors.DARK_GRAY)

        val map = _mapSvc.currentMap
        map?.tiles?.placed?.forEach { _, tile ->
            gc.renderImage(tile.tileSet.image, tile.x.d, tile.y.d, imgPart = tile.region)
        }
    }

    override fun handleMouse(evt: MouseEvent) {
        if( evt.type == MouseEvent.MouseEventType.PRESSED && evt.button == MouseEvent.MouseButton.LEFT) {
            val tileSel = _tileSvc.tileSelection ?: return
            val map = _mapSvc.currentMap ?: return
            map.tiles.placeTile(PlacedTileK(evt.point.x, evt.point.y, tileSel.tile, tileSel.region))
            redrawObs.trigger{it.invoke()}
        }
    }

    override val redrawObs = Observable<() -> Unit>()

    // Contracts
}

object MapWorkControllerProvider {
    val controller = lazy { MapWorkController(
        BackendDI.mapSvc.value,
        BackendDI.tileSvc.value) }
}