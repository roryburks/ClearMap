package clearmap.backend.external

interface IDialog {
    fun pickFile(kind: String) : String?
}