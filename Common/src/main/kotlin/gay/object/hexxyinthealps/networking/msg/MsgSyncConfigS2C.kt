package gay.`object`.hexxyinthealps.networking.msg

import gay.`object`.hexxyinthealps.config.HexxyInTheAlpsConfig
import net.minecraft.network.FriendlyByteBuf

data class MsgSyncConfigS2C(val serverConfig: HexxyInTheAlpsConfig.ServerConfig) : HexxyInTheAlpsMessageS2C {
    companion object : HexxyInTheAlpsMessageCompanion<MsgSyncConfigS2C> {
        override val type = MsgSyncConfigS2C::class.java

        override fun decode(buf: FriendlyByteBuf) = MsgSyncConfigS2C(
            serverConfig = HexxyInTheAlpsConfig.ServerConfig.decode(buf),
        )

        override fun MsgSyncConfigS2C.encode(buf: FriendlyByteBuf) {
            serverConfig.encode(buf)
        }
    }
}
