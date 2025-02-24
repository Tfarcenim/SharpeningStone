package tfar.sharpeningstone;

import net.minecraft.core.registries.Registries;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegisterEvent;
import tfar.sharpeningstone.datagen.ModDatagen;

@Mod(SharpeningStone.MOD_ID)
public class SharpeningStoneForge {
    
    public SharpeningStoneForge() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER,SSConfig.SERVER_SPEC);
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::register);
        bus.addListener(ModDatagen::start);
        // This method is invoked by the Forge mod loader when it is ready
        // to load your mod. You can access Forge and Common code in this
        // project.
    
        // Use Forge to bootstrap the Common mod.
        SharpeningStone.init();
    }

    void register(RegisterEvent event) {
        event.register(Registries.BLOCK,SharpeningStone.id("sharpening_stone"),() -> Init.SHARPENING_STONE);
        event.register(Registries.ITEM,SharpeningStone.id("sharpening_stone"),() -> Init.SHARPENING_STONE_ITEM);

        event.register(Registries.BLOCK,SharpeningStone.id("repair_bench"),() -> Init.REPAIR_BENCH);
        event.register(Registries.ITEM,SharpeningStone.id("repair_bench"),() -> Init.REPAIR_BENCH_ITEM);
        event.register(Registries.ITEM,SharpeningStone.id("repair_kit"),() -> Init.REPAIR_KIT);

        event.register(Registries.ITEM,SharpeningStone.id("portable_grindstone"),() -> Init.PORTABLE_GRINDSTONE);
    }
}