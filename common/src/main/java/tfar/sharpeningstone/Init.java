package tfar.sharpeningstone;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class Init {

    public static final Block SHARPENING_STONE = new SharpeningStoneBlock(BlockBehaviour.Properties.copy(Blocks.GRINDSTONE));
    public static final Item SHARPENING_STONE_ITEM = new BlockItem(SHARPENING_STONE,new Item.Properties());

}
