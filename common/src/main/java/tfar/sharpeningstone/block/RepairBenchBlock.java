package tfar.sharpeningstone.block;

import net.minecraft.core.BlockPos;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LevelEvent;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import tfar.sharpeningstone.Init;

public class RepairBenchBlock extends Block {


    public RepairBenchBlock(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult p_53826_) {
        ItemStack stack = player.getItemInHand(hand);
        InteractionHand other = hand == InteractionHand.MAIN_HAND ? InteractionHand.OFF_HAND : InteractionHand.MAIN_HAND;
        ItemStack opposite = player.getItemInHand(other);
        if (stack.isDamaged() && opposite.is(Init.REPAIR_KIT)) {
            player.awardStat(Stats.ITEM_USED.get(Init.REPAIR_KIT));
            if (!level.isClientSide) {
                stack.removeTagKey(ItemStack.TAG_DAMAGE);
                if (!player.getAbilities().instabuild) {
                    opposite.shrink(1);
                }
                level.levelEvent(LevelEvent.SOUND_SMITHING_TABLE_USED, pos, 0);
            }
            return InteractionResult.sidedSuccess(level.isClientSide);
        }
        return InteractionResult.PASS;
    }

}
