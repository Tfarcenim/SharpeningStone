package tfar.sharpeningstone;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import tfar.sharpeningstone.block.RepairBenchBlock;
import tfar.sharpeningstone.block.SharpeningStoneBlock;
import tfar.sharpeningstone.item.PortableGrindstoneItem;

public class Init {

    public static final Block SHARPENING_STONE = new SharpeningStoneBlock(BlockBehaviour.Properties.copy(Blocks.GRINDSTONE));
    public static final Item SHARPENING_STONE_ITEM = new BlockItem(SHARPENING_STONE,new Item.Properties());
    public static final Block REPAIR_BENCH = new RepairBenchBlock(BlockBehaviour.Properties.copy(Blocks.SMITHING_TABLE));
    public static final Item REPAIR_BENCH_ITEM = new BlockItem(REPAIR_BENCH,new Item.Properties());
    public static final Item REPAIR_KIT = new Item(new Item.Properties());
    public static final Item PORTABLE_GRINDSTONE = new PortableGrindstoneItem(new Item.Properties().durability(32));


}
