package clearmap.backend.tiles

import clearmap.backend.map.TileSet
import clearmap.backend.util.tileSlicer.ILineTileSlicer
import clearmap.backend.util.tileSlicer.LineTileSlicer
import rb.glow.GLowDependencySet
import rb.glow.img.IImageLoader

interface ITileSetLoader  {
    fun loadTileSet(filename: String) : TileSet?
}

class TileSetLoader(
    private val _imageLoader: IImageLoader,
    private val _lineTileSlicer: ILineTileSlicer
) : ITileSetLoader{
    override fun loadTileSet(filename: String): TileSet? {
        val file = _imageLoader.loadImageGl(filename) ?: return null

        val sliced = _lineTileSlicer.slice(file)
        return TileSet(file, sliced.toList())
    }
}

object TileSetLoaderProvider {
    val loader = lazy { TileSetLoader(
        GLowDependencySet.imageLoader.value,
        LineTileSlicer) }

}