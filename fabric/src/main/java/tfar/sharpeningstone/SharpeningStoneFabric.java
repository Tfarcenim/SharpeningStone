package tfar.sharpeningstone;

import fuzs.forgeconfigapiport.api.config.v2.ForgeConfigRegistry;
import net.fabricmc.api.ModInitializer;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraftforge.fml.config.ModConfig;

public class SharpeningStoneFabric implements ModInitializer {
    
    @Override
    public void onInitialize() {
        ForgeConfigRegistry.INSTANCE.register(SharpeningStone.MOD_ID, ModConfig.Type.SERVER, SSConfig.SERVER_SPEC);
        register();
        // This method is invoked by the Fabric mod loader when it is ready
        // to load your mod. You can access Fabric and Common code in this
        // project.

        // Use Fabric to bootstrap the Common mod.
        SharpeningStone.init();
    }
    
    void register() {
        Registry.register(BuiltInRegistries.BLOCK,SharpeningStone.id("sharpening_stone"),Init.SHARPENING_STONE);
        Registry.register(BuiltInRegistries.ITEM,SharpeningStone.id("sharpening_stone"),Init.SHARPENING_STONE_ITEM);

        Registry.register(BuiltInRegistries.BLOCK,SharpeningStone.id("repair_bench"),Init.REPAIR_BENCH);
        Registry.register(BuiltInRegistries.ITEM,SharpeningStone.id("repair_bench"),Init.REPAIR_BENCH_ITEM);
        Registry.register(BuiltInRegistries.ITEM,SharpeningStone.id("repair_kit"),Init.REPAIR_KIT);

        Registry.register(BuiltInRegistries.ITEM,SharpeningStone.id("portable_grindstone"),Init.PORTABLE_GRINDSTONE);
    }
    
}
