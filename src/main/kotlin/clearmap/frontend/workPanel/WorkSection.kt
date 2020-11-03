package clearmap.frontend.workPanel

import clearmap.backend.IMasterService
import sgui.Orientation
import sgui.components.IComponent
import sgui.components.IComponentProvider
import sgui.components.crossContainer.ICrossPanel

class WorkSectionView(
    private val ui: IComponentProvider,
    private val panel : ICrossPanel = ui.CrossPanel()
)  : IComponent by panel
{
    private val workAreaContainer = ui.CrossPanel()
    private val zoomPanel = ui.CrossPanel()
    private val vScroll = ui.ScrollBar(Orientation.VERTICAL, this)
    private val hScroll = ui.ScrollBar(Orientation.HORIZONTAL, this)
    private val coordinateLabel = ui.Label()
    private val messageLabel = ui.Label()

    init {
        vScroll.scrollWidth = 50
        hScroll.scrollWidth = 50

        val glWorkArea = JOGLWorkAreaPanel()
        //hScroll.scrollBind.addObserver { new, _ -> currentView?.offsetX = new * scrollRatio}
        //vScroll.scrollBind.addObserver { new, _ -> currentView?.offsetY = new * scrollRatio}
        workAreaContainer.setLayout { rows.add(glWorkArea.component) }
        //workAreaContainer.onMouseWheelMoved

        //workAreaContainer.onResize += {calibrateScrolls()}
        //Hybrid.timing.createTimer(15, true) {Hybrid.gle.runInGLContext { penner.step() }}

        coordinateLabel.text = "Coordinate Label"

        val barSize = 16
        panel.setLayout {
            rows += {
                add(workAreaContainer, flex = 200f)
                add(vScroll, width = barSize)
                flex = 200f
            }
            rows += {
                add(hScroll, flex = 200f)
                add(zoomPanel, width = barSize)
                height = barSize
            }
            rows += {
                add(coordinateLabel)
                addGap(0,3,Int.MAX_VALUE)
                add(messageLabel)
                height = 24
            }
        }
    }
}