package tfar.sharpeningstone;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantment;
import tfar.sharpeningstone.platform.Services;

import java.util.List;
import java.util.Map;

public class SharpeningStoneClient {

    public static void changeTooltips(ItemStack stack, List<Component> tooltip, TooltipFlag flags) {
        if (flags.isAdvanced()) {
            Map<Enchantment,Integer> degrading_map = SSConfig.CONFIG.degrading_map.get().get(stack.getItem());
            if (degrading_map!= null) {
                CompoundTag tag = stack.getTagElement(SharpeningStone.KEY);
                for (Enchantment enchantment : Services.PLATFORM.getEnchantments(stack).keySet()) {
                    if (degrading_map.containsKey(enchantment)) {
                        int maxDamage = degrading_map.get(enchantment);
                        int damage = tag == null ? 0 : tag.getInt(BuiltInRegistries.ENCHANTMENT.getKey(enchantment).toString());
                        insertAfter(tooltip,enchantment,damage,maxDamage);
                    }
                }
            }
        }
    }

    static void insertAfter(List<Component> tooltip,Enchantment enchantment,int damage,int maxDamage){
        int index =-1;
        for (int i = 0 ; i < tooltip.size();i++) {
            Component component = tooltip.get(i);
            if (component.getContents() instanceof TranslatableContents translatableContents) {
                String transKey = translatableContents.getKey();
                if (transKey.equals(enchantment.getDescriptionId())) {
                    index = i;
                    break;
                }
            }
        }
        if (index > 0) {
            tooltip.add(index+1, Component.literal("Remaining uses: "+(maxDamage - damage)+"/"+maxDamage));
        }
    }
}
