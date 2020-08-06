package sgui.menus

import sgui.UIPoint

interface IContextMenus<T> {
    fun LaunchContextMenu(point: UIPoint, scheme: List<MenuItem<T>>, obj: Any? = null)
}
