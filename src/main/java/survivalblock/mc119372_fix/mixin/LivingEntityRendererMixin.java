package survivalblock.mc119372_fix.mixin;

import net.minecraft.client.render.entity.GuardianEntityRenderer;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.RotationAxis;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntityRenderer.class)
public class LivingEntityRendererMixin {

    @Inject(method = "setupTransforms", at = @At("RETURN"))
    private void pitchGuardian(LivingEntity entity, MatrixStack matrices, float animationProgress, float bodyYaw, float tickDelta, float scale, CallbackInfo ci) {
        if (!((LivingEntityRenderer) (Object) this instanceof GuardianEntityRenderer)) {
            return;
        }
        float halfHeight = entity.getType().getHeight() / 2;
        matrices.translate(0, halfHeight, 0);
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(-entity.getPitch()));
        matrices.translate(0, -halfHeight, 0);
    }
}
