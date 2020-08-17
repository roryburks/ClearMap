package clearmapJvm.frontend

import clearmap.backend.IMasterService
import clearmap.frontend.workPanel.WorkSectionView
import sgui.menus.MenuItem
import sguiSwing.components.SwMenuBar
import sguiSwing.components.jcomponent
import sguiSwing.hybrid.Hybrid
import sguiSwing.menus.SwContextMenus
import java.awt.Dimension
import java.awt.GridLayout
import javax.swing.JFrame
import javax.swing.SwingUtilities


class RootWindow(
    private  val _master : IMasterService)
    : JFrame()
{
    val work = WorkSectionView(_master, Hybrid.ui)
    val panel = Hybrid.ui.CrossPanel()

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

        panel.setLayout {
            rows.addFlatGroup {
                add(work, flex = 1f)
                flex = 1f
            }
        }

        this.layout = GridLayout()
        this.add(panel.jcomponent)

        SwingUtilities.invokeLater {this.size = Dimension(1400,800) }
    }

}