package gay.`object`.hexxyinthealps.forge

import gay.`object`.hexxyinthealps.HexxyInTheAlpsClient
import net.minecraftforge.client.ConfigScreenHandler.ConfigScreenFactory
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import thedarkcolour.kotlinforforge.forge.LOADING_CONTEXT

object ForgeHexxyInTheAlpsClient {
    @Suppress("UNUSED_PARAMETER")
    fun init(event: FMLClientSetupEvent) {
        HexxyInTheAlpsClient.init()
        LOADING_CONTEXT.registerExtensionPoint(ConfigScreenFactory::class.java) {
            ConfigScreenFactory { _, parent -> HexxyInTheAlpsClient.getConfigScreen(parent) }
        }
    }
}
