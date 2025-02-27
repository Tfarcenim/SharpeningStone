package tfar.sharpeningstone;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.IEventBus;

public class SharpeningStoneClientForge {

    static void init(IEventBus bus) {
        MinecraftForge.EVENT_BUS.addListener(SharpeningStoneClientForge::tooltips);
    }

    static void tooltips(ItemTooltipEvent event) {
        SharpeningStoneClient.changeTooltips(event.getItemStack(),event.getToolTip(),event.getFlags());
    }

}
