package mekanism.client.gui.element.filter.miner;

import java.util.function.Consumer;
import mekanism.client.gui.IGuiWrapper;
import mekanism.client.gui.element.GuiElement;
import mekanism.client.gui.element.button.MekanismImageButton;
import mekanism.client.gui.element.slot.GuiSlot;
import mekanism.client.gui.element.slot.SlotType;
import mekanism.client.sound.SoundHandler;
import mekanism.common.MekanismLang;
import mekanism.common.content.miner.MinerFilter;
import mekanism.common.util.MekanismUtils;
import mekanism.common.util.MekanismUtils.ResourceType;
import mekanism.common.util.StackUtils;
import mekanism.common.util.text.BooleanStateDisplay.YesNo;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvents;

public interface GuiMinerFilterHelper {

    default void addMinerDefaults(IGuiWrapper gui, MinerFilter<?> filter, int slotOffset, Consumer<GuiElement> childAdder) {
        childAdder.accept(new GuiSlot(SlotType.NORMAL, gui, getRelativeX() + 148, getRelativeY() + slotOffset).setRenderHover(true));
        childAdder.accept(new MekanismImageButton(gui, gui.getLeft() + getRelativeX() + 148, gui.getTop() + getRelativeY() + 45, 14, 16,
              MekanismUtils.getResource(ResourceType.GUI_BUTTON, "exclamation.png"), () -> filter.requireStack = !filter.requireStack,
              (onHover, xAxis, yAxis) -> gui.displayTooltip(MekanismLang.MINER_REQUIRE_REPLACE.translate(YesNo.of(filter.requireStack)), xAxis, yAxis)));
    }

    default void renderReplaceStack(IGuiWrapper gui, MinerFilter<?> filter) {
        if (!filter.replaceStack.isEmpty()) {
            gui.getItemRenderer().zLevel += 200;
            gui.renderItem(filter.replaceStack, getRelativeX() + 149, getRelativeY() + 19);
            gui.getItemRenderer().zLevel -= 200;
        }
    }

    int getRelativeX();

    int getRelativeY();

    default boolean tryClickReplaceStack(IGuiWrapper gui, double mouseX, double mouseY, int button, int slotOffset, MinerFilter<?> filter) {
        if (button == 0) {
            double xAxis = mouseX - gui.getLeft();
            double yAxis = mouseY - gui.getTop();
            //Over replace output
            if (xAxis >= getRelativeX() + 149 && xAxis <= getRelativeX() + 165 &&
                yAxis >= getRelativeY() + slotOffset + 1 && yAxis <= getRelativeY() + slotOffset + 17) {
                ItemStack stack = GuiElement.minecraft.player.inventory.getItemStack();
                if (Screen.hasShiftDown()) {
                    filter.replaceStack = ItemStack.EMPTY;
                } else if (!stack.isEmpty() && stack.getItem() instanceof BlockItem) {
                    filter.replaceStack = StackUtils.size(stack, 1);
                } else {
                    return false;
                }
                SoundHandler.playSound(SoundEvents.UI_BUTTON_CLICK);
                return true;
            }
        }
        return false;
    }
}