package thedroyt.coalbeancraft.mixin;

import net.minecraft.entity.damage.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(DamageSource.class)
public interface DamageSourceInvoker {

    @Invoker("<init>")
    static DamageSource createDamageSource(String name) {
        throw new AssertionError();
    }

    @Invoker("setBypassesArmor")
    DamageSource invokeSetBypassesArmor();

    @Invoker("setUnblockable")
    DamageSource invokeSetUnblockable();
}
