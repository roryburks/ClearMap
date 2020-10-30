package testUtil

import javafx.scene.canvas.GraphicsContext
import rb.glow.gl.GLGraphicsContext
import rb.glow.gl.GLImage
import rb.glow.gle.IGLEngine
import rb.glow.img.RawImage
import rbJvm.vectrix.SetupVectrixForJvm
import sguiSwing.hybrid.EngineLaunchpoint
import sguiSwing.hybrid.Hybrid
import java.awt.image.BufferedImage
import javax.imageio.ImageIO


object TestUtil_File {
    fun doWithGL( lambda: (IGLEngine)->Unit) {
        SetupVectrixForJvm()
        val gle = EngineLaunchpoint.gle
        gle.runInGLContext{ GLGraphicsContext(100,100, false, gle).clear()}
        gle.runInGLContext{ lambda(gle)}
    }

    fun loadImage(filename: String) : GLImage {
        lateinit var img: RawImage
        var loader = TestUtil_File::class.java.classLoader
        loader.getResource(filename).openStream().use {
            img =  Hybrid.imageIO.loadImage(it.readBytes())
        }

        val gle = EngineLaunchpoint.gle
        return gle.converter.convertToGL(img, gle)
    }
}