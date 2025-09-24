package gay.`object`.hexxyinthealps

import at.petrak.hexcasting.api.casting.eval.vm.CastingImage
import at.petrak.hexcasting.api.casting.eval.vm.CastingVM
import at.petrak.hexcasting.api.casting.iota.Iota
import com.google.common.base.Stopwatch
import gay.`object`.hexxyinthealps.config.HexxyInTheAlpsServerConfig
import gay.`object`.hexxyinthealps.networking.HexxyInTheAlpsNetworking
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.level.ServerLevel
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.util.concurrent.TimeUnit
import kotlin.reflect.jvm.jvmName

object HexxyInTheAlps {
    const val MODID = "hexxyinthealps"

    @JvmField
    val LOGGER: Logger = LogManager.getLogger(MODID)

    @JvmStatic
    fun id(path: String) = ResourceLocation(MODID, path)

    fun init() {
        HexxyInTheAlpsServerConfig.init()
        initRegistries()
        HexxyInTheAlpsNetworking.init()
    }

    fun initServer() {
        HexxyInTheAlpsServerConfig.initServer()
    }

    @JvmStatic
    fun recordExecutionTime(
        vm: CastingVM,
        iotas: List<Iota>,
        world: ServerLevel,
        initialImage: CastingImage,
        stopwatch: Stopwatch,
    ) {
        // TODO: record historical data
        if (stopwatch.elapsed(TimeUnit.MILLISECONDS) >= HexxyInTheAlpsServerConfig.config.longCastThresholdMillis) {
            val message = buildString {
                // general info
                appendLine(
                    """
                    A single cast took $stopwatch to execute!
                        Dimension: ${world.dimension().location()}
                        Position: ${vm.env.mishapSprayPos()}
                        Ops consumed: ${vm.image.opsConsumed}
                        Environment: ${vm.env::class.qualifiedName ?: vm.env::class.jvmName}
                    """.trimIndent()
                )

                // caster info
                append("    ")
                appendLine(
                    vm.env.castingEntity?.let {
                        """
                        Caster:
                                Type: ${it.type.description.string}
                                Name: ${it.name.string}
                                Position: ${it.position()}
                                UUID: ${it.uuid}
                        """.trimIndent()
                    } ?: "Caster: null"
                )

                // iotas cast
                append("    Hex: [")
                iotas.joinTo(this) { it.display().string }
                appendLine("]")

                // initial stack if it wasn't empty
                if (initialImage.stack.isNotEmpty()) {
                    appendLine("    Initial stack:")
                    for (iota in initialImage.stack.asReversed()) {
                        append("        ")
                        appendLine(iota.display().string)
                    }
                }
            }.trim()
            LOGGER.warn(message)
        }
    }
}
