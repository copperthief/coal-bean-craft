package thedroyt.coalbeancraft.mixin;

import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(AbstractFurnaceBlockEntity.class)
public interface AbstractFurnaceBlockEntityAccessor {

    @Accessor("burnTime")
    public int getBurnTime();

    @Accessor("burnTime")
    public void setBurnTime(int burnTime);

    @Accessor("cookTime")
    public int getCookTime();

    @Accessor("cookTime")
    public void setCookTime(int cookTime);

    @Accessor("cookTimeTotal")
    public int getCookTimeTotal();

    @Accessor("cookTimeTotal")
    public void setCookTimeTotal(int cookTimeTotal);

    @Accessor("fuelTime")
    public int getFuelTime();

    @Accessor("fuelTime")
    public void setFuelTime(int fuelTime);

    @Invoker("isBurning")
    public boolean invokeIsBurning();
}
