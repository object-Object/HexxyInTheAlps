package gay.`object`.hexxyinthealps

import gay.`object`.hexxyinthealps.config.HexxyInTheAlpsConfig
import gay.`object`.hexxyinthealps.config.HexxyInTheAlpsConfig.GlobalConfig
import me.shedaniel.autoconfig.AutoConfig
import net.minecraft.client.gui.screens.Screen

object HexxyInTheAlpsClient {
    fun init() {
        HexxyInTheAlpsConfig.initClient()
    }

    fun getConfigScreen(parent: Screen): Screen {
        return AutoConfig.getConfigScreen(GlobalConfig::class.java, parent).get()
    }
}
