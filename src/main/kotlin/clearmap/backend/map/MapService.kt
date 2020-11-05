package clearmap.backend.map

import rb.owl.bindable.Bindable
import rb.owl.bindable.IBindable


interface IMapService {
    var currentMap : CWMapBlob?
    val currentMapBind: IBindable<CWMapBlob?>
}

class MapService : IMapService{
    override val currentMapBind = Bindable<CWMapBlob?>( CWMapBlob(CwMapK(listOf())))
    override var currentMap by currentMapBind
}


object MapServiceProvider {
    val Svc : Lazy<IMapService> = lazy { MapService() }

}