package rbJvm.glow

import rb.global.ILogger
import rb.glow.gl.GLImage
import rb.glow.gle.IGLEngine
import rb.glow.img.IImageLoader
import rbJvm.glow.awt.ImageBI
import java.io.File
import java.lang.Exception
import javax.imageio.ImageIO

class JvmImageLoader(
    private val _gle: IGLEngine,
    private val _logger: ILogger
) : IImageLoader {
    override fun loadImageGl(filename: String): GLImage? {
        val file = File(filename)
        if( !file.exists()) return null
        try {
            val imageBi = ImageBI(ImageIO.read(file))
            return _gle.converter.convertToGL(imageBi, _gle)
        }catch (e: Exception)
        {
            _logger.logWarning("Failure on Loading image.  Filename: $filename  Error: ${e.message}")
            return null
        }
    }
}