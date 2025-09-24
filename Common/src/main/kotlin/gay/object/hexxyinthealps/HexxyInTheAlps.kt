package gay.`object`.hexxyinthealps

import gay.`object`.hexxyinthealps.config.HexxyInTheAlpsServerConfig
import gay.`object`.hexxyinthealps.networking.HexxyInTheAlpsNetworking
import net.minecraft.resources.ResourceLocation
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

object HexxyInTheAlps {
    const val MODID = "hexxyinthealps"

    @JvmField
    val LOGGER: Logger = LogManager.getLogger(MODID)

    @JvmStatic
    fun id(path: String) = ResourceLocation(MODID, path)

    fun init() {
        HexxyInTheAlpsServerConfig.init()
        initRegistries()
        HexxyInTheAlpsNetworking.init()
    }

    fun initServer() {
        HexxyInTheAlpsServerConfig.initServer()
    }
}
