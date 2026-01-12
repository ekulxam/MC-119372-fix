package survivalblock.mc119372_fix.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.GuardianEntityModel;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(GuardianEntityModel.class)
public class GuardianEntityModelMixin {

    @WrapOperation(
            method = "setAngles(Lnet/minecraft/entity/mob/GuardianEntity;FFFFF)V",
            at = {
                    @At(value = "FIELD", target = "Lnet/minecraft/client/model/ModelPart;yaw:F", opcode = Opcodes.PUTFIELD, ordinal = 0),
                    @At(value = "FIELD", target = "Lnet/minecraft/client/model/ModelPart;pitch:F", opcode = Opcodes.PUTFIELD, ordinal = 0)
            }
    )
    private void doNotChangeHeadPitchOrYaw(ModelPart instance, float value, Operation<Void> original) {
        original.call(instance, 0.0F);
    }
}
