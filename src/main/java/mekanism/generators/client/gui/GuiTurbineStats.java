package mekanism.generators.client.gui;

import java.util.Arrays;
import mekanism.api.EnumColor;
import mekanism.client.gui.GuiMekanismTile;
import mekanism.client.gui.element.GuiEnergyInfo;
import mekanism.common.config.MekanismConfig;
import mekanism.common.inventory.container.ContainerNull;
import mekanism.common.util.LangUtils;
import mekanism.common.util.MekanismUtils;
import mekanism.common.util.MekanismUtils.ResourceType;
import mekanism.common.util.text.TextComponentUtil;
import mekanism.common.util.text.EnergyDisplay;
import mekanism.common.util.text.Translation;
import mekanism.generators.client.gui.element.GuiTurbineTab;
import mekanism.generators.client.gui.element.GuiTurbineTab.TurbineTab;
import mekanism.generators.common.content.turbine.TurbineUpdateProtocol;
import mekanism.generators.common.tile.turbine.TileEntityTurbineCasing;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GuiTurbineStats extends GuiMekanismTile<TileEntityTurbineCasing> {

    public GuiTurbineStats(PlayerInventory inventory, TileEntityTurbineCasing tile) {
        super(tile, new ContainerNull(inventory.player, tile));
        ResourceLocation resource = getGuiLocation();
        addGuiElement(new GuiTurbineTab(this, tileEntity, TurbineTab.MAIN, resource));
        addGuiElement(new GuiEnergyInfo(() -> {
            double producing = tileEntity.structure == null ? 0 : tileEntity.structure.clientFlow * (MekanismConfig.current().general.maxEnergyPerSteam.val() / TurbineUpdateProtocol.MAX_BLADES) *
                                                                  Math.min(tileEntity.structure.blades, tileEntity.structure.coils * MekanismConfig.current().generators.turbineBladesPerCoil.val());
            return Arrays.asList(TextComponentUtil.build(Translation.of("mekanism.gui.storing"), ": ", EnergyDisplay.of(tileEntity.getEnergy(), tileEntity.getMaxEnergy())),
                  TextComponentUtil.build(Translation.of("mekanism.gui.producing"), ": ", EnergyDisplay.of(producing), "/t"));
        }, this, resource));
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String stats = LangUtils.localize("gui.turbineStats");
        String limiting = EnumColor.DARK_RED + " (" + LangUtils.localize("gui.limiting") + ")";
        drawString(stats, (xSize / 2) - (font.getStringWidth(stats) / 2), 6, 0x404040);
        if (tileEntity.structure != null) {
            int lowerVolume = tileEntity.structure.lowerVolume;
            int clientDispersers = tileEntity.structure.clientDispersers;
            int vents = tileEntity.structure.vents;
            drawString(LangUtils.localize("gui.tankVolume") + ": " + lowerVolume, 8, 26, 0x404040);
            boolean dispersersLimiting = lowerVolume * clientDispersers * MekanismConfig.current().generators.turbineDisperserGasFlow.val()
                                         < vents * MekanismConfig.current().generators.turbineVentGasFlow.val();
            boolean ventsLimiting = lowerVolume * clientDispersers * MekanismConfig.current().generators.turbineDisperserGasFlow.val()
                                    > vents * MekanismConfig.current().generators.turbineVentGasFlow.val();
            drawString(LangUtils.localize("gui.steamFlow"), 8, 40, 0x797979);
            drawString(LangUtils.localize("gui.dispersers") + ": " + clientDispersers + (dispersersLimiting ? limiting : ""), 14, 49, 0x404040);
            drawString(LangUtils.localize("gui.vents") + ": " + vents + (ventsLimiting ? limiting : ""), 14, 58, 0x404040);
            int coils = tileEntity.structure.coils;
            int blades = tileEntity.structure.blades;
            drawString(LangUtils.localize("gui.production"), 8, 72, 0x797979);
            drawString(LangUtils.localize("gui.blades") + ": " + blades + (coils * 4 > blades ? limiting : ""), 14, 81, 0x404040);
            drawString(LangUtils.localize("gui.coils") + ": " + coils + (coils * 4 < blades ? limiting : ""), 14, 90, 0x404040);
            double energyMultiplier = (MekanismConfig.current().general.maxEnergyPerSteam.val() / TurbineUpdateProtocol.MAX_BLADES) *
                                      Math.min(blades, coils * MekanismConfig.current().generators.turbineBladesPerCoil.val());
            double rate = lowerVolume * (clientDispersers * MekanismConfig.current().generators.turbineDisperserGasFlow.val());
            rate = Math.min(rate, vents * MekanismConfig.current().generators.turbineVentGasFlow.val());
            drawString(LangUtils.localize("gui.maxProduction") + ": " + MekanismUtils.getEnergyDisplay(rate * energyMultiplier), 8, 104, 0x404040);
            drawString(LangUtils.localize("gui.maxWaterOutput") + ": " + tileEntity.structure.condensers * MekanismConfig.current().generators.condenserRate.val() +
                            " mB/t", 8, 113, 0x404040);
        }
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
    }

    @Override
    protected ResourceLocation getGuiLocation() {
        return MekanismUtils.getResource(ResourceType.GUI, "GuiNull.png");
    }
}