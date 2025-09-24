package gay.`object`.hexxyinthealps

import gay.`object`.hexxyinthealps.config.HexxyInTheAlpsClientConfig
import me.shedaniel.autoconfig.AutoConfig
import net.minecraft.client.gui.screens.Screen

object HexxyInTheAlpsClient {
    fun init() {
        HexxyInTheAlpsClientConfig.init()
    }

    fun getConfigScreen(parent: Screen): Screen {
        return AutoConfig.getConfigScreen(HexxyInTheAlpsClientConfig.GlobalConfig::class.java, parent).get()
    }
}
