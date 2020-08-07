package clearmapJvm.frontend

import clearmap.backend.IMasterService
import sgui.menus.MenuItem
import sguiSwing.components.SwMenuBar
import sguiSwing.menus.SwContextMenus
import javax.swing.JFrame


class RootWindow(
    private  val _master : IMasterService)
    : JFrame()
{
    init {
        val scheme = listOf<MenuItem<IMasterService>>(
            MenuItem("&File"),
            MenuItem(".&New Map"),
            MenuItem(".&Load Map"),
            MenuItem(".&Save Map"),
            MenuItem(".Save Map &As")
        )
        val bar = SwMenuBar()
        SwContextMenus(_master.commandExecutor).constructMenu(bar, scheme)
        jMenuBar = bar
    }

}