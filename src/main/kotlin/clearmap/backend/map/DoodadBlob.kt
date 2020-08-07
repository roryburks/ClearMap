package clearmap.backend.map

import rb.owl.bindable.Bindable

class DoodadBlob(
    colX1: Int,
    colY1: Int,
    colX2: Int,
    colY2: Int,
    img: IImageHandle)
{
    val colX1Bind = Bindable(colX1)
    val colY1Bind = Bindable(colY1)
    val colX2Bind = Bindable(colX2)
    val colY2Bind = Bindable(colY2)

    var colX1 by colX1Bind
    var colY1 by colY1Bind
    var colX2 by colX2Bind
    var colY2 by colY2Bind
}