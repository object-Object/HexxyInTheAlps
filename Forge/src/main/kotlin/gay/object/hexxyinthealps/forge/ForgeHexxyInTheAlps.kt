package gay.`object`.hexxyinthealps.forge

import dev.architectury.platform.forge.EventBuses
import gay.`object`.hexxyinthealps.HexxyInTheAlps
import gay.`object`.hexxyinthealps.forge.datagen.ForgeHexxyInTheAlpsDatagen
import net.minecraftforge.fml.common.Mod
import thedarkcolour.kotlinforforge.forge.MOD_BUS

@Mod(HexxyInTheAlps.MODID)
class ForgeHexxyInTheAlps {
    init {
        MOD_BUS.apply {
            EventBuses.registerModEventBus(HexxyInTheAlps.MODID, this)
            addListener(ForgeHexxyInTheAlpsClient::init)
            addListener(ForgeHexxyInTheAlpsDatagen::init)
        }
        HexxyInTheAlps.init()
    }
}
