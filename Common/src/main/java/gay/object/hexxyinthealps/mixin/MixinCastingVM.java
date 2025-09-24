package gay.object.hexxyinthealps.mixin;

import at.petrak.hexcasting.api.casting.eval.ExecutionClientView;
import at.petrak.hexcasting.api.casting.eval.ResolvedPatternType;
import at.petrak.hexcasting.api.casting.eval.vm.CastingImage;
import at.petrak.hexcasting.api.casting.eval.vm.CastingVM;
import at.petrak.hexcasting.api.casting.iota.Iota;
import at.petrak.hexcasting.api.casting.mishaps.Mishap;
import com.google.common.base.Stopwatch;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import gay.object.hexxyinthealps.HexxyInTheAlps;
import net.minecraft.server.level.ServerLevel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.ArrayList;
import java.util.List;

@Mixin(CastingVM.class)
public abstract class MixinCastingVM {
    @Invoker(remap = false)
    abstract CastingImage callGetImage();

    @SuppressWarnings("UnreachableCode")
    @WrapMethod(method = "queueExecuteAndWrapIotas", remap = false)
    private ExecutionClientView hexxyinthealps$measureExecutionTime_notTheCauseOfTickLag(
        List<? extends Iota> iotas,
        ServerLevel world,
        Operation<ExecutionClientView> original
    ) {
        var initialImage = callGetImage();
        var stopwatch = Stopwatch.createStarted();
        ExecutionClientView result;
        try {
            result = original.call(iotas, world);
        } catch (Exception e) {
            HexxyInTheAlps.LOGGER.error("Uncaught exception in CastingVM.queueExecuteAndWrapIotas", e);
            result = new ExecutionClientView(false, ResolvedPatternType.ERRORED, new ArrayList<>(), null);
        } catch (Throwable e) {
            // the compiler won't let us specifically catch Mishap, so catch everything and rethrow non-Mishaps
            // anything that isn't Exception or Mishap is likely an Error, so we shouldn't try to prevent it from crashing the server
            //noinspection ConstantValue
            if (!(e instanceof Mishap)) throw e;
            HexxyInTheAlps.LOGGER.error("Uncaught mishap in CastingVM.queueExecuteAndWrapIotas", e);
            result = new ExecutionClientView(false, ResolvedPatternType.ERRORED, new ArrayList<>(), null);
        }
        stopwatch.stop();
        HexxyInTheAlps.recordExecutionTime((CastingVM)(Object)this, iotas, world, initialImage, stopwatch);
        return result;
    }
}
