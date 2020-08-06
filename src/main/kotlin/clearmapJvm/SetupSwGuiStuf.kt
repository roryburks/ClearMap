package clearmapJvm

import sguiSwing.PrimaryIcon.*
import sguiSwing.SwPrimaryIconSet
import sguiSwing.SwProvider
import sguiSwing.hybrid.EngineLaunchpoint

fun setupSwGuiStuff() {
    SwProvider.converter = EngineLaunchpoint.converter

    SwPrimaryIconSet.setIcon(SmallExpanded, ClearmapIcons.SmallIcons.Expanded)
    SwPrimaryIconSet.setIcon(SmallExpandedHighlighted, ClearmapIcons.SmallIcons.ExpandedHighlighted)
    SwPrimaryIconSet.setIcon(SmallUnexpanded, ClearmapIcons.SmallIcons.Unexpanded)
    SwPrimaryIconSet.setIcon(SmallUnexpandedHighlighted, ClearmapIcons.SmallIcons.UnexpandedHighlighted)

    SwPrimaryIconSet.setIcon(SmallArrowN, ClearmapIcons.SmallIcons.ArrowN)
    SwPrimaryIconSet.setIcon(SmallArrowS, ClearmapIcons.SmallIcons.ArrowS)
    SwPrimaryIconSet.setIcon(SmallArrowE, ClearmapIcons.SmallIcons.ArrowE)
    SwPrimaryIconSet.setIcon(SmallArrowW, ClearmapIcons.SmallIcons.ArrowW)
}