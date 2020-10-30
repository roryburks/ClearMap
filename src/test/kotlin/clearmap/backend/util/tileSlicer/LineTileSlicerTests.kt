package clearmap.backend.util.tileSlicer

import org.junit.jupiter.api.Test
import rb.glow.Colors
import rb.glow.drawer
import rb.glow.gl.GLImage
import sguiSwing.hybrid.Hybrid
import testUtil.TestUtil_File
import java.io.File


object LineTileSlicerTests {
    @Test
    fun test() {
        TestUtil_File.doWithGL { gle ->
            val img =  TestUtil_File.loadImage("tile-splitting-test-1.png")

            val regions = LineTileSlicer.slice(img)

            println(regions.count())
        }
    }

}