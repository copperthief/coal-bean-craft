package thedroyt.coalbeancraft;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import thedroyt.coalbeancraft.block.FOTBlockEntity;
import thedroyt.coalbeancraft.block.FOTScreenHandler;
import thedroyt.coalbeancraft.registry.CoalBeanBlocks;
import thedroyt.coalbeancraft.registry.CoalBeanItems;
import thedroyt.coalbeancraft.registry.CoalBeanPaintings;

public class CoalBeanCraft implements ModInitializer {
    public static final String ID = "coalbeancraft";

    public static BlockEntityType<FOTBlockEntity> FOT_BLOCK_ENTITY;

    @Override
    public void onInitialize() {
        CoalBeanBlocks.register();
        CoalBeanItems.register();
        CoalBeanPaintings.register();

        FOT_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(ID, "furnace_of_truth"), BlockEntityType.Builder.create(FOTBlockEntity::new, CoalBeanBlocks.FOT).build(null));

    }


}
