package tfar.sharpeningstone;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Container;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.GrindstoneMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.Map;

public class PortableGrindstoneMenu extends GrindstoneMenu {
    private final ItemStack itemInHand;

    public PortableGrindstoneMenu(int containerId, Inventory playerInventory, ContainerLevelAccess access, ItemStack itemInHand) {
        super(containerId, playerInventory, access);
        this.itemInHand = itemInHand;
        replaceSlot(new Slot(this.resultSlots, 2, 129, 34) {
            /**
             * Check if the stack is allowed to be placed in this slot, used for armor slots as well as furnace fuel.
             */
            public boolean mayPlace(ItemStack stack) {
                return false;
            }

            public void onTake(Player player, ItemStack stack) {
                access.execute((p_39634_, p_39635_) -> {
                    if (p_39634_ instanceof ServerLevel) {
                        ExperienceOrb.award((ServerLevel)p_39634_, Vec3.atCenterOf(p_39635_), this.getExperienceAmount(p_39634_));
                    }

                    p_39634_.levelEvent(1042, p_39635_, 0);
                });
                repairSlots.setItem(0, ItemStack.EMPTY);
                repairSlots.setItem(1, ItemStack.EMPTY);
            }

            /**
             * Returns the total amount of XP stored in all the input slots of this container. The return value is randomized, so that it returns between 50% and 100% of the total XP.
             */
            private int getExperienceAmount(Level level) {
                int l = 0;
                l += this.getExperienceFromItem(repairSlots.getItem(0));
                l += this.getExperienceFromItem(repairSlots.getItem(1));
                if (l > 0) {
                    int i1 = (int)Math.ceil((double)l / 2.0D);
                    return i1 + level.random.nextInt(i1);
                } else {
                    return 0;
                }
            }

            /**
             * Returns the total amount of XP stored in the enchantments of this stack.
             */
            private int getExperienceFromItem(ItemStack stack) {
                int l = 0;
                Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(stack);

                for(Map.Entry<Enchantment, Integer> entry : map.entrySet()) {
                    Enchantment enchantment = entry.getKey();
                    Integer integer = entry.getValue();
                    if (!enchantment.isCurse()) {
                        l += enchantment.getMinCost(integer);
                    }
                }

                return l;
            }
        },2);
    }

    protected void replaceSlot(Slot slot, int slotID) {
        slots.set(slotID,slot);
        slot.index = slotID;
    }

    @Override
    public void slotsChanged(Container inventory) {
        super.slotsChanged(inventory);
    }

    @Override
    public boolean stillValid(Player player) {
        return !itemInHand.isEmpty();
    }
}
