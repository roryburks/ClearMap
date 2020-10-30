package clearmap.backend.map

import rb.owl.bindable.Bindable


class PlacedDoodadBlob(val base: DoodadBlob ) {
    val xBind = Bindable(0)
    val yBind = Bindable(0)

    var x by xBind
    var y by yBind
}