package gay.`object`.hexxyinthealps.config

import dev.architectury.event.events.client.ClientPlayerEvent
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

object HexxyInTheAlpsConfig {
    @JvmStatic
    lateinit var holder: ConfigHolder<GlobalConfig>

    @JvmStatic
    val client get() = holder.config.client

    @JvmStatic
    val server get() = syncedServerConfig ?: holder.config.server

    // only used on the client, probably
    private var syncedServerConfig: ServerConfig? = null

    fun init() {
        holder = AutoConfig.register(
            GlobalConfig::class.java,
            PartitioningSerializer.wrap(::Toml4jConfigSerializer),
        )

        PlayerEvent.PLAYER_JOIN.register { player ->
            MsgSyncConfigS2C(holder.config.server).sendToPlayer(player)
        }
    }

    fun initClient() {
        ClientPlayerEvent.CLIENT_PLAYER_QUIT.register { _ ->
            syncedServerConfig = null
        }
    }

    fun onSyncConfig(serverConfig: ServerConfig) {
        syncedServerConfig = serverConfig
    }

    @Config(name = HexxyInTheAlps.MODID)
    class GlobalConfig : GlobalData() {
        @Category("client")
        @TransitiveObject
        val client = ClientConfig()

        @Category("server")
        @TransitiveObject
        val server = ServerConfig()
    }

    @Config(name = "client")
    class ClientConfig : ConfigData

    @Config(name = "server")
    class ServerConfig : ConfigData {
        fun encode(buf: FriendlyByteBuf) {}

        companion object {
            fun decode(buf: FriendlyByteBuf) = ServerConfig().apply {}
        }
    }
}
