package mekanism.client.gui.robit;

import mekanism.common.entity.EntityRobit;
import mekanism.common.inventory.container.robit.ContainerRobitInventory;
import mekanism.common.util.LangUtils;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GuiRobitInventory extends GuiRobit {

    public GuiRobitInventory(PlayerInventory inventory, EntityRobit entity) {
        super(entity, new ContainerRobitInventory(inventory, entity));
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        drawString(LangUtils.localize("gui.robit.inventory"), 8, 6, 0x404040);
        drawString(LangUtils.localize("container.inventory"), 8, ySize - 93, 0x404040);
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
    }

    @Override
    protected String getBackgroundImage() {
        return "GuiRobitInventory.png";
    }

    @Override
    protected boolean shouldOpenGui(RobitGuiType guiType) {
        return guiType != RobitGuiType.INVENTORY;
    }
}