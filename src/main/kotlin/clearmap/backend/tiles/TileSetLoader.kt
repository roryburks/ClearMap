package clearmap.backend.tiles

import clearmap.backend.map.TileSet
import rb.glow.GLowDependencySet
import rb.glow.img.IImageLoader

interface ITileSetLoader  {
    fun loadTileSet(filename: String) : TileSet
}

class TileSetLoader(
    private val _imageLoader: IImageLoader = GLowDependencySet.imageLoader.value
) : ITileSetLoader{
    override fun loadTileSet(filename: String): TileSet {
        throw NotImplementedError()
    }
}