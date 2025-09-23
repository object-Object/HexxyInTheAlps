@file:JvmName("HexxyInTheAlpsAbstractionsImpl")

package gay.`object`.hexxyinthealps.fabric

import gay.`object`.hexxyinthealps.registry.HexxyInTheAlpsRegistrar
import net.minecraft.core.Registry

fun <T : Any> initRegistry(registrar: HexxyInTheAlpsRegistrar<T>) {
    val registry = registrar.registry
    registrar.init { id, value -> Registry.register(registry, id, value) }
}
