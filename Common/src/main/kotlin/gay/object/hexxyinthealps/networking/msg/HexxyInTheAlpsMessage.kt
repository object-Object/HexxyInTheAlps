package gay.`object`.hexxyinthealps.networking.msg

import dev.architectury.networking.NetworkChannel
import dev.architectury.networking.NetworkManager.PacketContext
import gay.`object`.hexxyinthealps.HexxyInTheAlps
import gay.`object`.hexxyinthealps.networking.HexxyInTheAlpsNetworking
import gay.`object`.hexxyinthealps.networking.handler.applyOnClient
import gay.`object`.hexxyinthealps.networking.handler.applyOnServer
import net.fabricmc.api.EnvType
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.server.level.ServerPlayer
import java.util.function.Supplier

sealed interface HexxyInTheAlpsMessage

sealed interface HexxyInTheAlpsMessageC2S : HexxyInTheAlpsMessage {
    fun sendToServer() {
        HexxyInTheAlpsNetworking.CHANNEL.sendToServer(this)
    }
}

sealed interface HexxyInTheAlpsMessageS2C : HexxyInTheAlpsMessage {
    fun sendToPlayer(player: ServerPlayer) {
        HexxyInTheAlpsNetworking.CHANNEL.sendToPlayer(player, this)
    }

    fun sendToPlayers(players: Iterable<ServerPlayer>) {
        HexxyInTheAlpsNetworking.CHANNEL.sendToPlayers(players, this)
    }
}

sealed interface HexxyInTheAlpsMessageCompanion<T : HexxyInTheAlpsMessage> {
    val type: Class<T>

    fun decode(buf: FriendlyByteBuf): T

    fun T.encode(buf: FriendlyByteBuf)

    fun apply(msg: T, supplier: Supplier<PacketContext>) {
        val ctx = supplier.get()
        when (ctx.env) {
            EnvType.SERVER, null -> {
                HexxyInTheAlps.LOGGER.debug("Server received packet from {}: {}", ctx.player.name.string, this)
                when (msg) {
                    is HexxyInTheAlpsMessageC2S -> msg.applyOnServer(ctx)
                    else -> HexxyInTheAlps.LOGGER.warn("Message not handled on server: {}", msg::class)
                }
            }
            EnvType.CLIENT -> {
                HexxyInTheAlps.LOGGER.debug("Client received packet: {}", this)
                when (msg) {
                    is HexxyInTheAlpsMessageS2C -> msg.applyOnClient(ctx)
                    else -> HexxyInTheAlps.LOGGER.warn("Message not handled on client: {}", msg::class)
                }
            }
        }
    }

    fun register(channel: NetworkChannel) {
        channel.register(type, { msg, buf -> msg.encode(buf) }, ::decode, ::apply)
    }
}
