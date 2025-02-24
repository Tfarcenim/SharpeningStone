package tfar.sharpeningstone;

import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;
import tfar.sharpeningstone.block.SharpeningStoneBlock;

import java.util.HashMap;
import java.util.Map;

public class SSConfig {
    public static final SSConfig CONFIG;
    public static final ForgeConfigSpec SERVER_SPEC;

    static {
        final Pair<SSConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(SSConfig::new);
        SERVER_SPEC = specPair.getRight();
        CONFIG = specPair.getLeft();
    }

    public final ConfigHelper.ConfigObject<Map<TagKey<Item>, EnchantmentInstance>> map;
    public final ForgeConfigSpec.DoubleValue damage;

    public SSConfig(ForgeConfigSpec.Builder builder)  {
        builder.push("general");
        map = ConfigHelper.defineObject(builder,"map", SharpeningStoneBlock.CODEC,defaults());
        damage = builder.defineInRange("damage",.0625,0,1);
        builder.pop();
    }

    static Map<TagKey<Item>,EnchantmentInstance> defaults() {
        Map<TagKey<Item>,EnchantmentInstance> map =  new HashMap<>();
        map.put(ItemTags.SWORDS,new EnchantmentInstance(Enchantments.SHARPNESS,3));
        map.put(ModTags.DIGGERS,new EnchantmentInstance(Enchantments.BLOCK_EFFICIENCY,3));
        return map;
    }


}
