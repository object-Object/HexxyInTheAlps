@file:JvmName("HexxyInTheAlpsAbstractions")

package gay.`object`.hexxyinthealps

import dev.architectury.injectables.annotations.ExpectPlatform
import gay.`object`.hexxyinthealps.registry.HexxyInTheAlpsRegistrar

fun initRegistries(vararg registries: HexxyInTheAlpsRegistrar<*>) {
    for (registry in registries) {
        initRegistry(registry)
    }
}

@ExpectPlatform
fun <T : Any> initRegistry(registrar: HexxyInTheAlpsRegistrar<T>) {
    throw AssertionError()
}
