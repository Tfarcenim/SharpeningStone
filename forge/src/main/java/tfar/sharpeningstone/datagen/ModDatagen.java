package tfar.sharpeningstone.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import tfar.sharpeningstone.datagen.assets.ModBlockstateProvider;
import tfar.sharpeningstone.datagen.assets.ModItemModelProvider;
import tfar.sharpeningstone.datagen.assets.ModLangProvider;
import tfar.sharpeningstone.datagen.data.ModBlockTagsProvider;
import tfar.sharpeningstone.datagen.data.ModItemTagsProvider;
import tfar.sharpeningstone.datagen.data.ModLootTableProvider;
import tfar.sharpeningstone.datagen.data.ModRecipeProvider;

import java.util.concurrent.CompletableFuture;

public class ModDatagen {

    public static void start(GatherDataEvent e) {
        DataGenerator generator = e.getGenerator();
        ExistingFileHelper helper = e.getExistingFileHelper();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = e.getLookupProvider();


        if (e.includeClient()) {
            generator.addProvider(true,new ModLangProvider(output));
            generator.addProvider(true,new ModBlockstateProvider(output,helper));
            generator.addProvider(true,new ModItemModelProvider(output,helper));
        }
        if (e.includeServer()) {
            generator.addProvider(true, ModLootTableProvider.create(output));
            generator.addProvider(true,new ModRecipeProvider(output));

            ModBlockTagsProvider blockTags = new ModBlockTagsProvider(output,lookupProvider, helper);
            generator.addProvider(true,blockTags);

            generator.addProvider(true,new ModItemTagsProvider(output,lookupProvider,blockTags.contentsGetter(),helper));
        }
    }
}
