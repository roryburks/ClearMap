package cwShared.dialogSystem

import clearmapJvm.ClearmapIcons // TODO: Fix
import sguiSwing.components.jcomponent
import javax.swing.JOptionPane

class AbstractDialogRunner {

    fun <T> runDialogPanel(panel: IDialogPanel<T>) = when(JOptionPane.showConfirmDialog(
            null,
            panel.jcomponent,
            "New Layer",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE,
            ClearmapIcons.BigIcons.NewLayer.icon))
    {
        JOptionPane.OK_OPTION -> panel.result
        else -> null
    }
}