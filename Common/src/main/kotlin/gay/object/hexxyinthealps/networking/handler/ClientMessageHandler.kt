package gay.`object`.hexxyinthealps.networking.handler

import dev.architectury.networking.NetworkManager.PacketContext
import gay.`object`.hexxyinthealps.config.HexxyInTheAlpsServerConfig
import gay.`object`.hexxyinthealps.networking.msg.*

fun HexxyInTheAlpsMessageS2C.applyOnClient(ctx: PacketContext) = ctx.queue {
    when (this) {
        is MsgSyncConfigS2C -> {
            HexxyInTheAlpsServerConfig.onSyncConfig(serverConfig)
        }

        // add more client-side message handlers here
    }
}
