package clearmap.backend.external

interface IConfigLayer {
    fun getString( key: String) : String?
    fun getByteArray( key: String) : ByteArray?
    fun getInt( key: String, default: Int) : Int
    fun getBoolean( key: String, default: Boolean) : Boolean

    fun putString( key: String, value: String)
    fun putByteArray( key: String, value: ByteArray)
    fun putInt( key: String, value: Int)
    fun putBoolean( key: String, value: Boolean)

    fun remove( key: String)
}