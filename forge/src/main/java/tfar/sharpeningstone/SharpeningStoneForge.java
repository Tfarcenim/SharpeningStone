package tfar.sharpeningstone;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.registries.RegisterEvent;
import tfar.sharpeningstone.datagen.ModDatagen;

@Mod(SharpeningStone.MOD_ID)
public class SharpeningStoneForge {
    
    public SharpeningStoneForge() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER,SSConfig.SERVER_SPEC);
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::register);
        bus.addListener(ModDatagen::start);
        bus.addListener(this::tabs);
        MinecraftForge.EVENT_BUS.addListener(this::breakBlock);
        MinecraftForge.EVENT_BUS.addListener(this::hitMob);
        if (FMLEnvironment.dist.isClient()) {
            SharpeningStoneClientForge.init(bus);
        }
        // This method is invoked by the Forge mod loader when it is ready
        // to load your mod. You can access Forge and Common code in this
        // project.
    
        // Use Forge to bootstrap the Common mod.
        SharpeningStone.init();
    }

    void tabs(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(Init.SHARPENING_STONE);
            event.accept(Init.REPAIR_BENCH);
            event.accept(Init.REPAIR_KIT);
            event.accept(Init.PORTABLE_GRINDSTONE);
        }
    }

    void breakBlock(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        BlockPos pos = event.getPos();
        LevelAccessor level = event.getLevel();
        ItemStack stack = player.getMainHandItem();
        BlockState state = event.getState();
        if (state.getDestroySpeed(level,pos) > 0) {
            SharpeningStone.incrementDamage(stack);
        }
    }

    void hitMob(AttackEntityEvent event) {
        Player player = event.getEntity();
        ItemStack stack = player.getMainHandItem();
        SharpeningStone.incrementDamage(stack);
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