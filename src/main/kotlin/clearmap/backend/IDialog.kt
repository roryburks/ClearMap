package clearmap.backend

interface IDialog {
    fun pickFile(kind: String) : String?
}