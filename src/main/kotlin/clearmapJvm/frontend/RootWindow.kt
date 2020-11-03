package clearmapJvm.frontend

import clearmap.backend.IMasterService
import clearmap.backend.commands.CentralCommandExecutorProvider
import clearmap.backend.commands.ICentralCommandExecutor
import clearmap.frontend.root.RootController
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
    private  val _master : IMasterService,
    private val _commandExecutor : ICentralCommandExecutor = CentralCommandExecutorProvider.Svc.value
) : JFrame()
{
    val controller = RootController(Hybrid.ui)
    val panel = Hybrid.ui.CrossPanel()

    init {
        val scheme = controller.menuScheme
        val bar = SwMenuBar()
        SwContextMenus(_commandExecutor).constructMenu(bar, scheme)
        jMenuBar = bar

        controller.setLayout(panel)

        this.layout = GridLayout()
        this.add(panel.jcomponent)

        SwingUtilities.invokeLater {this.size = Dimension(1400,800) }
    }

}