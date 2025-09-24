package gay.`object`.hexxyinthealps.networking.msg

import gay.`object`.hexxyinthealps.config.HexxyInTheAlpsServerConfig
import net.minecraft.network.FriendlyByteBuf

data class MsgSyncConfigS2C(val serverConfig: HexxyInTheAlpsServerConfig.ServerConfig) : HexxyInTheAlpsMessageS2C {
    companion object : HexxyInTheAlpsMessageCompanion<MsgSyncConfigS2C> {
        override val type = MsgSyncConfigS2C::class.java

        override fun decode(buf: FriendlyByteBuf) = MsgSyncConfigS2C(
            serverConfig = HexxyInTheAlpsServerConfig.ServerConfig().decode(buf),
        )

        override fun MsgSyncConfigS2C.encode(buf: FriendlyByteBuf) {
            serverConfig.encode(buf)
        }
    }
}
