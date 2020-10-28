package testUtil

import rb.glow.gl.GLImage
import java.awt.image.BufferedImage
import javax.imageio.ImageIO


object TestUtil_File {
    fun loadImage(filename: String) : GLImage {
        lateinit var img: BufferedImage
        var loader = TestUtil_File::class.java.classLoader
        loader.getResource(filename).openStream().use {
            img = ImageIO.read(it)
        }

        throw NotImplementedError()
    }
}