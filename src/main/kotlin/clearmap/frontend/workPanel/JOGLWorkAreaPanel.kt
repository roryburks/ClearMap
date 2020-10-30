package clearmap.frontend.workPanel

import com.jogamp.opengl.GLAutoDrawable
import com.jogamp.opengl.GLCapabilities
import com.jogamp.opengl.GLEventListener
import com.jogamp.opengl.GLProfile
import com.jogamp.opengl.awt.GLJPanel
import rb.glow.Colors
import rb.glow.IGraphicsContext
import rb.glow.drawer
import rb.glow.gl.GLGraphicsContext
import rbJvm.glow.jogl.JOGLProvider
import sgui.components.IComponent
import sguiSwing.components.SwComponent
import sguiSwing.hybrid.Hybrid

class JOGLWorkAreaPanel (
    val canvas: GLJPanel = GLJPanel(GLCapabilities(GLProfile.getDefault()))
) : IWorkArea
{
    override val component: IComponent = SwComponent(canvas)

    override fun injectDrawingRoutine(lambda: (IGraphicsContext) -> Unit) { }

    private var _lambda: ((IGraphicsContext) -> Unit)? = null

    fun doDraw(gc : IGraphicsContext) { _lambda?.invoke(gc) }


    val listener = object : GLEventListener {
        override fun reshape(drawable: GLAutoDrawable?, x: Int, y: Int, width: Int, height: Int) {}
        override fun dispose(drawable: GLAutoDrawable?) {}

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
            glgc.drawer.drawRect(10.0, 10.0, 200.0, 200.0)

            doDraw(glgc)
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
            subContext.makeCurrent()
            drawable.setContext(subContext, true)
        }
    }

    init {
        canvas.skipGLOrientationVerticalFlip = true
        canvas.addGLEventListener(listener)
    }
}