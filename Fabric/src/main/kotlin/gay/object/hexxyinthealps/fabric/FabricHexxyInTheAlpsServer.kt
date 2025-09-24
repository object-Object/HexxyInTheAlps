package gay.`object`.hexxyinthealps.fabric

import gay.`object`.hexxyinthealps.HexxyInTheAlps
import net.fabricmc.api.DedicatedServerModInitializer

object FabricHexxyInTheAlpsServer : DedicatedServerModInitializer {
    override fun onInitializeServer() {
        HexxyInTheAlps.initServer()
    }
}
