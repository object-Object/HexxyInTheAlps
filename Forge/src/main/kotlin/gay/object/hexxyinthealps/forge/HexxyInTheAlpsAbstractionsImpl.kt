@file:JvmName("HexxyInTheAlpsAbstractionsImpl")

package gay.`object`.hexxyinthealps.forge

import gay.`object`.hexxyinthealps.registry.HexxyInTheAlpsRegistrar
import net.minecraftforge.registries.RegisterEvent
import thedarkcolour.kotlinforforge.forge.MOD_BUS

fun <T : Any> initRegistry(registrar: HexxyInTheAlpsRegistrar<T>) {
    MOD_BUS.addListener { event: RegisterEvent ->
        event.register(registrar.registryKey) { helper ->
            registrar.init(helper::register)
        }
    }
}
