package tfar.sharpeningstone;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tfar.sharpeningstone.block.SharpeningStoneBlock;
import tfar.sharpeningstone.platform.Services;

import java.util.Map;
import java.util.Set;

// This class is part of the common project meaning it is shared between all supported loaders. Code written here can only
// import and access the vanilla codebase, libraries used by vanilla, and optionally third party libraries that provide
// common compatible binaries. This means common code can not directly use loader specific concepts such as Forge events
// however it will be compatible with all supported mod loaders.
public class SharpeningStone {

    public static final String MOD_ID = "sharpeningstone";
    public static final String MOD_NAME = "SharpeningStone";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);

    public static final String KEY = "sharpeningstone:degrading";

    // The loader specific projects are able to import and use any code from the common project. This allows you to
    // write the majority of your code here and load it from your loader specific projects. This example has some
    // code that gets invoked by the entry point of the loader specific projects.
    public static void init() {

    }

    public static ResourceLocation id(String path) {
        return new ResourceLocation(MOD_ID,path);
    }


    static void incrementDamage(ItemStack stack) {
        Map<Enchantment,Integer> map = SSConfig.CONFIG.degrading_map.get().get(stack.getItem());
        if (map != null) {
            Set<Enchantment> enchantments = Services.PLATFORM.getEnchantments(stack).keySet();
            for (Enchantment enchantment : enchantments) {
                if(map.containsKey(enchantment)) {
                   int maxDurability = map.get(enchantment);
                    CompoundTag tag = stack.getOrCreateTagElement(KEY);
                    String key = BuiltInRegistries.ENCHANTMENT.getKey(enchantment).toString();
                    int v = tag.getInt(key);
                    v++;
                    if (v >= maxDurability) {
                        SharpeningStoneBlock.downgradeEnchant(stack,enchantment);
                      v = 0;
                    }
                    tag.putInt(key,v);
                }
            }
        }
    }
}