package gay.`object`.hexxyinthealps.networking

import dev.architectury.networking.NetworkChannel
import gay.`object`.hexxyinthealps.HexxyInTheAlps
import gay.`object`.hexxyinthealps.networking.msg.HexxyInTheAlpsMessageCompanion

object HexxyInTheAlpsNetworking {
    val CHANNEL: NetworkChannel = NetworkChannel.create(HexxyInTheAlps.id("networking_channel"))

    fun init() {
        for (subclass in HexxyInTheAlpsMessageCompanion::class.sealedSubclasses) {
            subclass.objectInstance?.register(CHANNEL)
        }
    }
}
