package clearmapJvm.implementations

import clearmap.backend.external.IConfigLayer
import java.util.prefs.Preferences

class JvmConfigLayer(
    userNode : Class<*>
) : IConfigLayer {
    val preferences = Preferences.userNodeForPackage( userNode)

    override fun getString(key: String) = preferences.get(key, null)
    override fun getByteArray(key: String) = preferences.getByteArray(key, null)
    override fun getInt(key: String, default: Int) = preferences.getInt( key, default)
    override fun getBoolean(key: String, default: Boolean) = preferences.getBoolean( key, default)

    override fun putString(key: String, value: String) = preferences.put(key, value)
    override fun putByteArray(key: String, value: ByteArray) = preferences.putByteArray(key, value)
    override fun putInt(key: String, value: Int) = preferences.putInt(key, value)
    override fun putBoolean(key: String, value: Boolean) = preferences.putBoolean(key, value)

    override fun remove(key: String) = preferences.remove(key)
}
