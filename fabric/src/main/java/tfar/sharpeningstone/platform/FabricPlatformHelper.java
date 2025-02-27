package tfar.sharpeningstone.platform;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import tfar.sharpeningstone.platform.services.IPlatformHelper;
import net.fabricmc.loader.api.FabricLoader;

import java.util.Map;

public class FabricPlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatformName() {
        return "Fabric";
    }

    @Override
    public boolean isModLoaded(String modId) {

        return FabricLoader.getInstance().isModLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {

        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }

    @Override
    public Map<Enchantment, Integer> getEnchantments(ItemStack stack) {
        return Map.of();
    }
}
