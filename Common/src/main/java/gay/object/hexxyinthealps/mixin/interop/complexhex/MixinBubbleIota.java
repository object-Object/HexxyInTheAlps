package gay.object.hexxyinthealps.mixin.interop.complexhex;

import at.petrak.hexcasting.api.casting.iota.Iota;
import gay.object.hexxyinthealps.mixin.MixinIota;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Pseudo
@Mixin(targets = "dev.kineticcat.complexhex.api.casting.iota.BubbleIota", remap = false)
public abstract class MixinBubbleIota extends MixinIota {
    @Invoker
    public abstract <T extends Iota> T callGetContainedIota();

    @Override
    protected void hexxyinthealps$calculateSizeProperly(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(callGetContainedIota().size() + 1);
    }

    @Override
    protected void hexxyinthealps$calculateDepthProperly(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(callGetContainedIota().depth() + 1);
    }
}
