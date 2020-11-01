package clearmap.frontend.tiles

import clearmap.backend.IMasterService
import clearmap.backend.tiles.ITileService
import com.jogamp.opengl.GLAutoDrawable
import com.jogamp.opengl.GLCapabilities
import com.jogamp.opengl.GLEventListener
import com.jogamp.opengl.GLProfile
import com.jogamp.opengl.awt.GLJPanel
import rb.glow.Colors
import rb.glow.Composite
import rb.glow.gl.GLGraphicsContext
import rb.owl.bindable.addObserver
import rbJvm.glow.jogl.JOGLProvider
import sgui.components.IComponent
import sgui.components.IComponentProvider
import sgui.components.crossContainer.ICrossPanel
import sguiSwing.components.SwComponent
import sguiSwing.hybrid.Hybrid
import spirite.specialRendering.GLSpecialDrawer
import java.io.File

class TileView(
    private val _master : IMasterService,
    private val _ui: IComponentProvider,
    private val _panel: ICrossPanel)
    : IComponent by _panel
{
    constructor( master: IMasterService,ui: IComponentProvider)
            : this(master, ui, ui.CrossPanel())

    val label = _ui.Label("Test")
    val drawView = TileDrawView(_master.tileSvc)

    init {
        _panel.setLayout {
            rows.add(label)
            rows.add(drawView.component)
            rows.flex = 1f
        }
    }

    val tileObsK = _master.tileSvc.currentTileBind.addObserver { new, old -> drawView.component.redraw() }


}

class TileDrawView(private val _service : ITileService) {
    val canvas = GLJPanel(GLCapabilities(GLProfile.getDefault()))
    val component: IComponent = SwComponent(canvas)

    val listener = object  : GLEventListener {
        override fun reshape(drawable: GLAutoDrawable, x: Int, y: Int, width: Int, height: Int) { }
        override fun dispose(drawable: GLAutoDrawable) { }

        override fun display(drawable: GLAutoDrawable) {
            val w = drawable.surfaceWidth
            val h = drawable.surfaceHeight

            val gle = Hybrid.gle
            val glgc = GLGraphicsContext(w, h, false, gle, true)

            JOGLProvider.gl2 = drawable.gl.gL2
            gle.setTarget(null)
            glgc.clear()

            val gl = gle.gl
            gl.viewport(0, 0, w, h)

            glgc.color = Colors.RED

            val specialDrawer = GLSpecialDrawer(glgc)
            specialDrawer.drawTransparencyBg(0, 0, w, h, 8)

            val tile = _service.currentTile
            if( tile != null){
                glgc.alpha = 1f
                glgc.composite = Composite.SRC_OVER
                glgc.renderImage(tile.image, 0.0, 0.0)
                Hybrid.imageIO.saveImage(tile.image, File("C:\\bucket\\x2.png"))
            }

            JOGLProvider.gl2 = null
        }

        override fun init(drawable: GLAutoDrawable) {
            // Disassociate default workspace and assosciate the workspace from the GLEngine
            //	(so they can share resources)
            val primaryContext = JOGLProvider.context

            val unusedDefaultContext = drawable.context
            unusedDefaultContext.makeCurrent()
            drawable.setContext( null, true)

            val subContext = drawable.createContext( primaryContext)
            subContext?.makeCurrent()
            drawable.setContext(subContext, true)
        }
    }

    init {
        canvas.skipGLOrientationVerticalFlip = true
        canvas.addGLEventListener(listener)
    }

}