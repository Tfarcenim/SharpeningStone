package tfar.sharpeningstone.datagen.data;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import tfar.sharpeningstone.Init;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(PackOutput generatorIn) {
        super(generatorIn);
    }

    @Override
    public void buildRecipes(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, Init.PORTABLE_GRINDSTONE)
                .define('a', Blocks.SMOOTH_SANDSTONE)
                .define('b', Items.IRON_INGOT)
                .define('c', ItemTags.PLANKS)
                .pattern(" a ").pattern("bab").pattern("cac")
                .unlockedBy("has_iron", has(Items.IRON_INGOT)).save(consumer);
    }
}
