package tfar.sharpeningstone.datagen.assets;

import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;
import tfar.sharpeningstone.Init;
import tfar.sharpeningstone.SharpeningStone;

public class ModLangProvider extends LanguageProvider {
    public ModLangProvider(PackOutput gen) {
        super(gen, SharpeningStone.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add(Init.SHARPENING_STONE,"Sharpening Stone");
        add(Init.REPAIR_BENCH,"Repair Bench");
        add(Init.REPAIR_KIT,"Repair Kit");
        add(Init.PORTABLE_GRINDSTONE,"Portable Grindstone");
    }
}
