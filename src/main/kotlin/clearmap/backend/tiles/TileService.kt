package clearmap.backend.tiles

import clearmap.backend.IMasterService
import clearmap.backend.map.TileSet
import rb.owl.bindable.Bindable
import rb.owl.bindable.IBindable

interface ITileService {
    var currentTile: TileSet?
    val currentTileBind : IBindable<TileSet?>

    fun loadTileSet( fileName: String, selectByDefault: Boolean = true) : TileSet?
}

class TileService (
    private val _loader : ITileSetLoader
) : ITileService
{
    override val currentTileBind = Bindable<TileSet?>(null)
    override var currentTile by currentTileBind

    override fun loadTileSet(fileName: String, selectByDefault: Boolean): TileSet? {
        val set = _loader.loadTileSet(fileName) ?: return null

        if( selectByDefault) {
            currentTile = set
        }
        return set
    }
}

object TileServiceProvider {
    val svc = lazy { TileService( TileSetLoaderProvider.loader.value ) }
}
