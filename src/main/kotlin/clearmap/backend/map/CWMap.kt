package clearmap.backend.map

data class CwMapK(
    val placedTiles: List<PlacedTileK> )

class CWMapBlob(k: CwMapK)
{
    val tiles = CwMapPlacedTiles(k.placedTiles)
}

class CwMapPlacedTiles(placedTiles: List<PlacedTileK>) {
    private val _placedMap : MutableMap<Int, PlacedTileBlob> = mutableMapOf()
    private var _placedMet  : Int

    val placed : Map<Int,PlacedTileK> get() = _placedMap
        .map { Pair(it.key, it.value.k) }
        .toMap()

    fun getTile(i: Int) : PlacedTileBlob? = _placedMap[i]
    fun placeTile( k: PlacedTileK) : Int {
        val met = _placedMet++
        _placedMap[met] = PlacedTileBlob(k)
        return met
    }

    fun removeTile( i: Int) { _placedMap.remove(i) }

    init {
        var met = 0
        placedTiles.forEachIndexed { index, placedTileK ->
            _placedMap[index] = PlacedTileBlob(placedTileK)
            met = index+1
        }
        _placedMet = met
    }
}