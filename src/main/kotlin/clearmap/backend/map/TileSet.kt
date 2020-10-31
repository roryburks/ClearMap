package clearmap.backend.map

import rb.glow.img.IImage
import rb.vectrix.shapes.RectI

data class TileSet(
    val image: IImage,
    val tiles: List<RectI> )