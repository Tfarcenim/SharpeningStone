package tfar.sharpeningstone.datagen.data;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(PackOutput generatorIn) {
        super(generatorIn);
    }

    @Override
    public void buildRecipes(Consumer<FinishedRecipe> consumer) {
        /*ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS,ModItems.WHITE_UMBRELLA)
                .define('a', Blocks.WHITE_WOOL)
                .define('b', Items.SHIELD)
                .define('c', Items.IRON_INGOT)
                .pattern("aba").pattern(" c ").pattern(" c ")
                .unlockedBy("has_shield", has(Items.SHIELD)).save(consumer);*/


    }
}
