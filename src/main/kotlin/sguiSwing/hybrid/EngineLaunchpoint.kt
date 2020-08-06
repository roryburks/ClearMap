package sguiSwing.hybrid

import clearmapJvm.GL330ShaderLoader
import clearmapJvm.JClassScriptService
import rb.glow.gle.GLEngine
import rb.glow.gle.IGLEngine
import rbJvm.glow.awt.AwtImageConverter
import rbJvm.glow.jogl.GluPolygonTesselater
import rbJvm.glow.jogl.JOGLContext
import rbJvm.glow.jogl.JOGLProvider

/** This serves little purpose other than being the single place that everything else gets its GLEngine singleton */
object EngineLaunchpoint {
    val gle : IGLEngine by lazy {
        GLEngine(
                GluPolygonTesselater,
                GL330ShaderLoader(JOGLProvider.gl, JClassScriptService()),
                converter,
                JOGLContext())
    }

    val converter = AwtImageConverter {gle}
}