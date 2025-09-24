package gay.object.hexxyinthealps.mixin;

import at.petrak.hexcasting.api.casting.iota.Iota;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/*
from Linguardium on Discord:

mixin to the class with the method
your injected method in that mixin will have an empty body

mixin into the class you want to actually do stuff
extend the parent mixin
override the annotated method and actually do stuff

the method will be injected into the parent class with nothing. the inject will call the method. in every class except the child mixin target, the parent's method does nothing. overriding will override the method that the inject is calling
*/
@SuppressWarnings("CancellableInjectionUsage")
@Mixin(value = Iota.class, remap = false)
public abstract class MixinIota {
    @Inject(method = "size", at = @At("HEAD"), cancellable = true)
    protected void hexxyinthealps$calculateSizeProperly(CallbackInfoReturnable<Integer> cir) {}

    @Inject(method = "depth", at = @At("HEAD"), cancellable = true)
    protected void hexxyinthealps$calculateDepthProperly(CallbackInfoReturnable<Integer> cir) {}
}
