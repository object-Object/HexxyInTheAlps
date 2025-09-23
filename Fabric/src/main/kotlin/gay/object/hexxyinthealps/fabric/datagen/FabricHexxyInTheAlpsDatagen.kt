package gay.`object`.hexxyinthealps.fabric.datagen

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator

object FabricHexxyInTheAlpsDatagen : DataGeneratorEntrypoint {
    override fun onInitializeDataGenerator(gen: FabricDataGenerator) {
        @Suppress("UNUSED_VARIABLE")
        val pack = gen.createPack()
    }
}
