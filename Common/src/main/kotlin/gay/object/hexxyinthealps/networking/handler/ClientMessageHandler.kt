package gay.`object`.hexxyinthealps.networking.handler

import dev.architectury.networking.NetworkManager.PacketContext
import gay.`object`.hexxyinthealps.config.HexxyInTheAlpsConfig
import gay.`object`.hexxyinthealps.networking.msg.*

fun HexxyInTheAlpsMessageS2C.applyOnClient(ctx: PacketContext) = ctx.queue {
    when (this) {
        is MsgSyncConfigS2C -> {
            HexxyInTheAlpsConfig.onSyncConfig(serverConfig)
        }

        // add more client-side message handlers here
    }
}
