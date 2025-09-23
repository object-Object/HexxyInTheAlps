package gay.`object`.hexxyinthealps.fabric

import gay.`object`.hexxyinthealps.HexxyInTheAlps
import net.fabricmc.api.ModInitializer

object FabricHexxyInTheAlps : ModInitializer {
    override fun onInitialize() {
        HexxyInTheAlps.init()
    }
}
