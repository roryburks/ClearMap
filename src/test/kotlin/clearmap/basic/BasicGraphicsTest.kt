package clearmap.basic

import org.junit.jupiter.api.Test
import rb.glow.Colors
import rb.glow.drawer
import rb.glow.gl.GLImage
import sguiSwing.hybrid.Hybrid
import testUtil.TestUtil_File
import java.io.File

object BasicGraphicsTest  {
    @Test
    fun drawsRectangleGood() {
        TestUtil_File.doWithGL { gle ->
            val img = GLImage(100, 100, gle)
            val gc = img.graphics
            gc.clear()
            gc.gle.gl.viewport(0, 0, 100, 100)
            gc.color = Colors.RED
            gc.alpha = 1f

            gc.drawer.fillRect(10.0, 10.0, 40.0, 40.0)

            Hybrid.imageIO.saveImage(img, File("C:\\bucket\\x.png"))
            assert(img.getARGB(15,15) == Colors.RED.argb32)
        }
    }
}