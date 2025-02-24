package tfar.sharpeningstone.item;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import tfar.sharpeningstone.PortableGrindstoneMenu;

public class PortableGrindstoneItem extends Item {
    private static final Component CONTAINER_TITLE = Component.translatable("container.grindstone_title");


    public PortableGrindstoneItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();
        Level level = context.getLevel();
        if (player != null) {
            if (level.isClientSide) {
                return InteractionResult.SUCCESS;
            } else {
                player.openMenu(new SimpleMenuProvider(
                        (p_53812_, p_53813_, p_53814_) -> new PortableGrindstoneMenu(p_53812_, p_53813_, ContainerLevelAccess.create(level,BlockPos.ZERO),context.getItemInHand()), CONTAINER_TITLE));
                player.awardStat(Stats.INTERACT_WITH_GRINDSTONE);
                return InteractionResult.CONSUME;
            }
        }

        return InteractionResult.PASS;
    }
}
