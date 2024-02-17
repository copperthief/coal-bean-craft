package thedroyt.coalbeancraft.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class BadAppleMixin extends Entity {
    public BadAppleMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    private static final DamageSource BAD_APPLE = ((DamageSourceInvoker)((DamageSourceInvoker) DamageSourceInvoker.createDamageSource("badApple")).invokeSetBypassesArmor()).invokeSetUnblockable();

    @Inject(method = "applyFoodEffects", at = @At(value = "RETURN"))
    private void onApplyFoodEffects(ItemStack stack, World world, LivingEntity targetEntity, CallbackInfo ci) {
        if (stack.isItemEqual(Items.APPLE.getDefaultStack()))
            targetEntity.damage(BAD_APPLE, 42069);
    }

}
