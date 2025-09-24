package gay.`object`.hexxyinthealps.forge

import gay.`object`.hexxyinthealps.HexxyInTheAlps
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent

object ForgeHexxyInTheAlpsServer {
    @Suppress("UNUSED_PARAMETER")
    fun init(event: FMLDedicatedServerSetupEvent) {
        HexxyInTheAlps.initServer()
    }
}
