package gay.`object`.hexxyinthealps.fabric

import gay.`object`.hexxyinthealps.HexxyInTheAlpsClient
import net.fabricmc.api.ClientModInitializer

object FabricHexxyInTheAlpsClient : ClientModInitializer {
    override fun onInitializeClient() {
        HexxyInTheAlpsClient.init()
    }
}
