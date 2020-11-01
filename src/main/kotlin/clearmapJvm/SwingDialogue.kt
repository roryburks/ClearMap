package clearmapJvm

import clearmap.backend.IDialog
import java.io.File
import javax.swing.JFileChooser
import javax.swing.filechooser.FileNameExtensionFilter

class SwingDialogue :IDialog {
    private val previousFileMap = mutableMapOf<String, String>()

    override fun pickFile(kind: String): String? {
        val fc = JFileChooser()


        fc.choosableFileFilters.forEach { fc.removeChoosableFileFilter(it) }
        val filters=  listOf(
            FileNameExtensionFilter("PNG File", "png"),
            FileNameExtensionFilter("JPEG File", "jpg", "jpeg"),
            FileNameExtensionFilter("Bitmap File", "bmp"),
            fc.acceptAllFileFilter)
        filters.forEach { fc.addChoosableFileFilter(it) }

        val defaultFile = File(previousFileMap[kind] ?: "C:\\")

        fc.currentDirectory = defaultFile
        fc.selectedFile = defaultFile

        val result = fc.showOpenDialog(null)
        if( result == JFileChooser.APPROVE_OPTION) {
            var saveFile = fc.selectedFile

            previousFileMap[kind] = saveFile.absolutePath

            return saveFile.canonicalPath
        }

        return null
    }

}