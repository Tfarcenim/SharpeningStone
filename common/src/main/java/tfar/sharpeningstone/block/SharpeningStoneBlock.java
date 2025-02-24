package tfar.sharpeningstone.block;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.GrindstoneBlock;
import net.minecraft.world.level.block.LevelEvent;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import tfar.sharpeningstone.SSConfig;

import java.util.Map;

public class SharpeningStoneBlock extends GrindstoneBlock {

    public static final Codec<Map<TagKey<Item>,Enchantment>> CODEC = Codec.unboundedMap(TagKey.codec(Registries.ITEM), BuiltInRegistries.ENCHANTMENT.byNameCodec());

    public SharpeningStoneBlock(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult p_53826_) {
        ItemStack stack = player.getItemInHand(hand);

        var map = SSConfig.CONFIG.map.get();
        for (var entry: map.entrySet()) {
            var tag = entry.getKey();
            Enchantment enchantment = entry.getValue();
            if (stack.is(tag) && EnchantmentHelper.getItemEnchantmentLevel(enchantment,stack) < enchantment.getMaxLevel()) {
                if (!level.isClientSide) {
                    upgradeEnchant(stack, enchantment);
                    level.levelEvent(LevelEvent.SOUND_GRINDSTONE_USED, pos, 0);
                    stack.hurtAndBreak((int) (stack.getMaxDamage() * SSConfig.CONFIG.damage.get()),player, player1-> player1.broadcastBreakEvent(hand));
                }
                return InteractionResult.sidedSuccess(level.isClientSide);
            }
        }
        return InteractionResult.PASS;
    }

    static void upgradeEnchant(ItemStack stack,Enchantment enchantment) {
        int level = EnchantmentHelper.getItemEnchantmentLevel(enchantment,stack);
        cleanupDuplicates(stack,enchantment);
        stack.enchant(enchantment,level+1);
    }

    static void cleanupDuplicates(ItemStack stack, Enchantment enchantment) {
        CompoundTag tag = stack.getTag();
        if (stack.hasTag()) {
            ListTag listtag = tag.getList(ItemStack.TAG_ENCH, CompoundTag.TAG_COMPOUND);

            ResourceLocation enchantmentId = EnchantmentHelper.getEnchantmentId(enchantment);
            listtag.removeIf(tag1 -> ((CompoundTag) tag1).getString("id").equals(enchantmentId.toString()));
        }
    }

}
