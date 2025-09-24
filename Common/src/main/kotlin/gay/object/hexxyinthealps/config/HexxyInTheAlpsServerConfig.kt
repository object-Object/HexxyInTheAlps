package gay.`object`.hexxyinthealps.config

import dev.architectury.event.events.common.PlayerEvent
import gay.`object`.hexxyinthealps.HexxyInTheAlps
import gay.`object`.hexxyinthealps.networking.msg.MsgSyncConfigS2C
import me.shedaniel.autoconfig.AutoConfig
import me.shedaniel.autoconfig.ConfigData
import me.shedaniel.autoconfig.ConfigHolder
import me.shedaniel.autoconfig.annotation.Config
import me.shedaniel.autoconfig.annotation.ConfigEntry.Category
import me.shedaniel.autoconfig.annotation.ConfigEntry.Gui.TransitiveObject
import me.shedaniel.autoconfig.serializer.PartitioningSerializer
import me.shedaniel.autoconfig.serializer.PartitioningSerializer.GlobalData
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.world.InteractionResult

object HexxyInTheAlpsServerConfig {
    @JvmStatic
    lateinit var holder: ConfigHolder<GlobalConfig>

    @JvmStatic
    val config get() = syncedServerConfig ?: holder.config.server

    // only used on the client
    private var syncedServerConfig: ServerConfig? = null

    fun init() {
        holder = AutoConfig.register(
            GlobalConfig::class.java,
            PartitioningSerializer.wrap(::Toml4jConfigSerializer),
        )

        // prevent this holder from saving the server config; that happens in the client config gui
        holder.registerSaveListener { _, _ -> InteractionResult.FAIL }
    }

    fun initServer() {
        PlayerEvent.PLAYER_JOIN.register { player ->
            MsgSyncConfigS2C(holder.config.server).sendToPlayer(player)
        }
    }

    fun onSyncConfig(serverConfig: ServerConfig?) {
        syncedServerConfig = serverConfig
    }

    @Config(name = HexxyInTheAlps.MODID)
    class GlobalConfig(
        @Category("server")
        @TransitiveObject
        val server: ServerConfig = ServerConfig(),
    ) : GlobalData()

    @Config(name = "server")
    class ServerConfig : ConfigData {
        fun encode(buf: FriendlyByteBuf) {}

        fun decode(buf: FriendlyByteBuf): ServerConfig {
            return this
        }
    }
}
