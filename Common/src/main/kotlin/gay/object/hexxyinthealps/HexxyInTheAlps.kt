package gay.`object`.hexxyinthealps

import net.minecraft.resources.ResourceLocation
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import gay.`object`.hexxyinthealps.config.HexxyInTheAlpsConfig
import gay.`object`.hexxyinthealps.networking.HexxyInTheAlpsNetworking
import gay.`object`.hexxyinthealps.registry.HexxyInTheAlpsActions

object HexxyInTheAlps {
    const val MODID = "hexxyinthealps"

    @JvmField
    val LOGGER: Logger = LogManager.getLogger(MODID)

    @JvmStatic
    fun id(path: String) = ResourceLocation(MODID, path)

    fun init() {
        HexxyInTheAlpsConfig.init()
        initRegistries(
            HexxyInTheAlpsActions,
        )
        HexxyInTheAlpsNetworking.init()
    }
}
