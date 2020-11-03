package clearmapJvm.implementations

import clearmap.backend.external.IConfigLayer
import clearmap.backend.external.IDialog
import java.io.File
import javax.swing.JFileChooser
import javax.swing.filechooser.FileNameExtensionFilter

private const val FILE_CONFIG_KEY = "file_configs"

class SwingDialogue (
    private val _config : IConfigLayer
) : IDialog {
    private val _fileConfig = FileSelectionConfigs()

    override fun pickFile(kind: String): String? {
        val fc = JFileChooser()


        fc.choosableFileFilters.forEach { fc.removeChoosableFileFilter(it) }
        val filters=  listOf(
            FileNameExtensionFilter("PNG File", "png"),
            FileNameExtensionFilter("JPEG File", "jpg", "jpeg"),
            FileNameExtensionFilter("Bitmap File", "bmp"),
            fc.acceptAllFileFilter)
        filters.forEach { fc.addChoosableFileFilter(it) }

        val defaultFile = File(_fileConfig.getFileForKind(kind) ?: "C:\\")

        fc.currentDirectory = defaultFile
        fc.selectedFile = defaultFile

        val result = fc.showOpenDialog(null)
        if( result == JFileChooser.APPROVE_OPTION) {
            val saveFile = fc.selectedFile

            _fileConfig.setFileForKind(kind, saveFile.absolutePath)

            return saveFile.canonicalPath
        }

        return null
    }

    private inner class FileSelectionConfigs {
        // Note: kind -> File maps are stored as config strings in the format
        // kind;C:\dir|kind2;C:\dir2
        // Since | is a forbidden letter (at least in Windows), this should be fine
        private var _loaded = false
        private val _map = mutableMapOf<String,String>()

        fun getFileForKind( kind: String) : String? {
            if(!_loaded) load()

            return _map[kind]
        }

        fun setFileForKind( kind: String, file: String) {
            if(!_loaded) load()
            _map[kind] = file
            save()
        }

        private fun load() {
            val map = _config.getString(FILE_CONFIG_KEY)
                ?.split('|')
            if( map != null) {
                for( entry in map) {
                    val split = entry.indexOf(';')
                    if( split < 0) continue
                    val key =  entry.substring(0, split)
                    val file = entry.substring(split+1)
                    _map[key] = file
                }
            }

            _loaded = true
        }

        private fun save() {
            val joinedString = _map
                .map { "${it.key};${it.value}" }
                .joinToString("|")
            _config.putString(FILE_CONFIG_KEY,joinedString)
        }
    }
}