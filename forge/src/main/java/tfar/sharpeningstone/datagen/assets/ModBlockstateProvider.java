package tfar.sharpeningstone.datagen.assets;

import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.GrindstoneBlock;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import tfar.sharpeningstone.Init;
import tfar.sharpeningstone.SharpeningStone;

public class ModBlockstateProvider extends BlockStateProvider {
    public ModBlockstateProvider(PackOutput gen, ExistingFileHelper exFileHelper) {
        super(gen, SharpeningStone.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        brewingStand();
        simpleBlock(Init.REPAIR_BENCH,models().withExistingParent("repair_bench",mcLoc("block/smithing_table")));
    }


    protected void brewingStand() {
        ModelFile modelFile = models().withExistingParent("sharpening_stone",mcLoc("block/grindstone"))
                .texture("round",modLoc("block/sharpening_stone_round"))
                .texture("side",modLoc("block/sharpening_stone_side"))
                ;
        getVariantBuilder(Init.SHARPENING_STONE).forAllStates(state -> {
            Direction direction = state.getValue(GrindstoneBlock.FACING);
            AttachFace face = state.getValue(GrindstoneBlock.FACE);
            int y = (int) direction.toYRot();
            int x = switch (face) {
                case WALL -> 90;
                case FLOOR -> 0;
                case CEILING -> 180;
            };
            return ConfiguredModel.builder().modelFile(modelFile).rotationX(x).rotationY(y).build();
        });
    }
}
