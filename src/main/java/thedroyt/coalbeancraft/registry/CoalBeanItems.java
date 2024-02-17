package thedroyt.coalbeancraft.registry;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import thedroyt.coalbeancraft.CoalBeanCraft;

import java.util.List;

public class CoalBeanItems {

    public static final ItemGroup COALBEANCRAFT = FabricItemGroupBuilder.build(
            new Identifier("tutorial", "general"),
            () -> new ItemStack(CoalBeanBlocks.AINSLEY_BLOCK));

    public static final Item WDOLLA = new Item(new FabricItemSettings().group(COALBEANCRAFT));

    public static void register() {
        reg(new BlockItem(CoalBeanBlocks.WEIRD_ASS_GRASS_BLOCK, new FabricItemSettings().group(COALBEANCRAFT)) {
            @Override
            public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
                tooltip.add(new TranslatableText("item.coalbeancraft.weird_ass_grass_block.bewildermenttooltip").formatted(Formatting.DARK_RED).formatted(Formatting.BOLD));
                tooltip.add(new LiteralText(""));
                tooltip.add(new TranslatableText("tooltip.coalbeancraft.awardedfor"));
                tooltip.add(new TranslatableText("item.coalbeancraft.weird_ass_grass_block.origintooltip").formatted(Formatting.LIGHT_PURPLE));
            }
        }, "weird_ass_grass_block");

        reg(new BlockItem(CoalBeanBlocks.AINSLEY_BLOCK, new FabricItemSettings().group(COALBEANCRAFT)), "ainsley_block");
        reg(WDOLLA, "wdolla");
    }

    private static void reg(Item item, String id) {
        Registry.register(Registry.ITEM, new Identifier(CoalBeanCraft.ID, id), item);
    }
}
