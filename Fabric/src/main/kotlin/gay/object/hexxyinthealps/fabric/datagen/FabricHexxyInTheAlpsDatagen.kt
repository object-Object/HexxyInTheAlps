package gay.`object`.hexxyinthealps.fabric.datagen

import gay.`object`.hexxyinthealps.datagen.HexxyInTheAlpsActionTags
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator

object FabricHexxyInTheAlpsDatagen : DataGeneratorEntrypoint {
    override fun onInitializeDataGenerator(gen: FabricDataGenerator) {
        val pack = gen.createPack()

        pack.addProvider(::HexxyInTheAlpsActionTags)
    }
}
