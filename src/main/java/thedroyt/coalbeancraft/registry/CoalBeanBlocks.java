package thedroyt.coalbeancraft.registry;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import thedroyt.coalbeancraft.CoalBeanCraft;
import thedroyt.coalbeancraft.block.DirectionalBlock;
import thedroyt.coalbeancraft.block.FOTBlock;

public class CoalBeanBlocks {

    public static final Block AINSLEY_BLOCK = new Block(AbstractBlock.Settings.of(Material.SOIL, MaterialColor.IRON).breakInstantly().noCollision().sounds(BlockSoundGroup.ANCIENT_DEBRIS));
    public static final Block WEIRD_ASS_GRASS_BLOCK = new DirectionalBlock(AbstractBlock.Settings.of(Material.SOLID_ORGANIC).strength(0.6F).sounds(BlockSoundGroup.GRASS));
    public static final Block FOT = new FOTBlock(AbstractBlock.Settings.of(Material.STONE));

    public static void register() {
        reg(AINSLEY_BLOCK, "ainsley_block");
        reg(WEIRD_ASS_GRASS_BLOCK, "weird_ass_grass_block");
        reg(FOT, "furnace_of_truth");
    }

    private static void reg(Block block, String id) {
        Registry.register(Registry.BLOCK, new Identifier(CoalBeanCraft.ID, id), block);
    }
}
