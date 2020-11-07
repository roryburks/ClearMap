package clearmap.frontend.workPanel.map.behaviors

import clearmap.backend.map.CWMapBlob
import clearmap.backend.map.PlacedTileK
import clearmap.backend.tiles.TileSelection
import clearmap.frontend.workPanel.map.MapWorkBehaviorEngine
import rb.glow.IGraphicsContext
import rb.vectrix.linear.Vec2
import rb.vectrix.linear.Vec2i
import sgui.Direction
import sgui.components.events.MouseEvent

class PlacingTileMapBehavior(
    engine: MapWorkBehaviorEngine,
    val tile: TileSelection,
    val map: CWMapBlob) : MapBehavior(engine)
{
    var placementMode : PlacementMode? = null
    var placedTile: PlacedTileK? = null

    override fun onStart() {
        val pos = getPos()
        placedTile = PlacedTileK(pos.xi, pos.yi, tile.tile, tile.region)
    }

    override fun onTick(evt: MouseEvent) {
        if( evt.type == MouseEvent.MouseEventType.RELEASED) {
            placedTile?.run { map.tiles.placeTile(this)}
            engine.clearBehavior()
        }
    }

    override fun draw(gc: IGraphicsContext) { placedTile?.draw(gc) }

    fun getPos() : Vec2i {
        return when(val mode = placementMode) {
            null -> {
                Vec2i(engine.x, engine.y)
            }
            is SnapToTilesPlacementMode -> {
                mode.findSnap(map, tile) ?: Vec2i(engine.x, engine.y)
            }
        }

    }
}

sealed class PlacementMode {}

class SnapToTilesPlacementMode(
    val orientation: Direction,
    val hSnap : Int = 16,
    val vSnap : Int = 16,
    val likeKindOnly: Boolean = true
) : PlacementMode()
{
    fun findSnap(map: CWMapBlob, tileSelection: TileSelection) : Vec2i? {

        return null
    }

}