package thedroyt.coalbeancraft.block;

import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeType;
import net.minecraft.screen.FurnaceScreenHandler;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.Nullable;
import thedroyt.coalbeancraft.CoalBeanCraft;
import thedroyt.coalbeancraft.mixin.AbstractFurnaceBlockEntityAccessor;
import thedroyt.coalbeancraft.registry.CoalBeanBlocks;
import thedroyt.coalbeancraft.registry.CoalBeanItems;

import static thedroyt.coalbeancraft.mixin.AbstractFurnaceBlockEntityAccessor.*;

public class FOTBlockEntity extends AbstractFurnaceBlockEntity {

    public FOTBlockEntity() {
        super(CoalBeanCraft.FOT_BLOCK_ENTITY, RecipeType.SMELTING);
    }

    @Override
    protected Text getContainerName() {
        return new TranslatableText("container.coalbeancraft.furnace_of_truth");
    }

    @Override
    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return new FurnaceScreenHandler(syncId, playerInventory, this, propertyDelegate);
    }

    @Override
    public void tick() {
        boolean bl = ((AbstractFurnaceBlockEntityAccessor)this).invokeIsBurning();
        boolean bl2 = false;
        if (((AbstractFurnaceBlockEntityAccessor)this).invokeIsBurning()) {   //decrement burn time
            ((AbstractFurnaceBlockEntityAccessor)this).setBurnTime(((AbstractFurnaceBlockEntityAccessor)this).getBurnTime() - 1);
        }

        if (!this.world.isClient) {
            ItemStack itemStack = (ItemStack)this.inventory.get(1); //decrement cook time if there's no fuel
            if (!((AbstractFurnaceBlockEntityAccessor)this).invokeIsBurning() && (itemStack.isEmpty() || ((ItemStack)this.inventory.get(0)).isEmpty())) {
                if (!((AbstractFurnaceBlockEntityAccessor)this).invokeIsBurning() && ((AbstractFurnaceBlockEntityAccessor)this).getCookTime() > 0) {
                    ((AbstractFurnaceBlockEntityAccessor)this).setCookTime(MathHelper.clamp(((AbstractFurnaceBlockEntityAccessor)this).getCookTime() - 2, 0, ((AbstractFurnaceBlockEntityAccessor)this).getCookTimeTotal()));
                }
            } else {
                if (!((AbstractFurnaceBlockEntityAccessor)this).invokeIsBurning()) {
                    ((AbstractFurnaceBlockEntityAccessor)this).setBurnTime(this.getFuelTime(itemStack));
                    ((AbstractFurnaceBlockEntityAccessor)this).setFuelTime(((AbstractFurnaceBlockEntityAccessor)this).getBurnTime()); //if it's not burning but there is fuel and something to burn, set the burn time to the fuel's burn time
                    if (((AbstractFurnaceBlockEntityAccessor)this).invokeIsBurning()) {
                        bl2 = true;
                        if (!itemStack.isEmpty()) {
                            Item item = itemStack.getItem(); //if burning now, decrement the fuel stack and handle the possibility that it's empty
                            itemStack.decrement(1);
                            if (itemStack.isEmpty()) {
                                Item item2 = item.getRecipeRemainder();
                                this.inventory.set(1, item2 == null ? ItemStack.EMPTY : new ItemStack(item2));
                            }
                        }
                    }
                }

                if (((AbstractFurnaceBlockEntityAccessor)this).invokeIsBurning()) {
                    ((AbstractFurnaceBlockEntityAccessor)this).setCookTime(((AbstractFurnaceBlockEntityAccessor)this).getCookTime() + 1);
                    if (((AbstractFurnaceBlockEntityAccessor)this).getCookTime() == ((AbstractFurnaceBlockEntityAccessor)this).getCookTimeTotal()) { //increment cooktime, reset if needed
                        ((AbstractFurnaceBlockEntityAccessor)this).setCookTime(0);
                        ((AbstractFurnaceBlockEntityAccessor)this).setCookTimeTotal(this.getCookTime());
                        output();
                        bl2 = true;
                    }
                } else {
                    ((AbstractFurnaceBlockEntityAccessor)this).setCookTime(0);
                }
            }

            if (bl != ((AbstractFurnaceBlockEntityAccessor)this).invokeIsBurning()) {
                bl2 = true;
                this.world.setBlockState(this.pos, (BlockState)this.world.getBlockState(this.pos).with(AbstractFurnaceBlock.LIT, ((AbstractFurnaceBlockEntityAccessor)this).invokeIsBurning()), 3);
            }
        }

        if (bl2) {
            this.markDirty();
        }
    }

    private void output() {
        if (this.inventory.get(2).isEmpty()) this.inventory.set(2, new ItemStack(CoalBeanBlocks.AINSLEY_BLOCK));
        else this.inventory.get(2).increment(1);
        this.inventory.get(0).decrement(1);
    }
}
