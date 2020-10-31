package clearmap.frontend.root

import clearmap.backend.IMasterService
import clearmap.frontend.tiles.TileView
import clearmap.frontend.workPanel.WorkSectionView
import sgui.components.IComponentProvider
import sgui.components.crossContainer.CrossInitializer
import sgui.components.crossContainer.ICrossPanel
import sgui.menus.MenuItem

class RootController(
    private val _master: IMasterService,
    private val _ui: IComponentProvider
) {
    val work = WorkSectionView(_master, _ui)
    val tileView = TileView(_master, _ui)

    val menuScheme get() = listOf<MenuItem<IMasterService>>(
        MenuItem("&File"),
        MenuItem(".&New Map"),
        MenuItem(".&Load Map"),
        MenuItem(".&Save Map"),
        MenuItem(".Save Map &As"),

        MenuItem("&Tiles"),
        MenuItem(".&Load Image as Tile")
    )

    fun setLayout(panel : ICrossPanel) {
        panel.setLayout {
            cols += {
                add(work, flex = 1f)
                flex = 1f
            }
            cols += {
                add(tileView, flex = 1f)
                width = 300
            }
        }

    }
}