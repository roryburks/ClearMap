package clearmap.backend.map

import rb.glow.IGraphicsContext
import rb.owl.Observable
import rb.owl.bindable.Bindable
import rb.vectrix.mathUtil.d
import rb.vectrix.shapes.RectI

data class PlacedTileK(
    val x: Int,
    val y: Int,
    val tileSet: TileSet,
    val region: RectI )
{
    fun draw( gc: IGraphicsContext) { gc.renderImage(tileSet.image, x.d, y.d, imgPart = region) }
}

class PlacedTileBlob(k: PlacedTileK){
    val xBind = Bindable(k.x)
    val yBind = Bindable(k.y)
    val regionBind = Bindable(k.region)
    val tileSet = k.tileSet

    var x by xBind
    var y by yBind
    var region by regionBind

    val k = PlacedTileK( x, y, tileSet, region)
}
