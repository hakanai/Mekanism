package mekanism.common.item.block;

import java.util.List;
import javax.annotation.Nonnull;
import mekanism.api.text.EnumColor;
import mekanism.client.MekKeyHandler;
import mekanism.client.MekanismKeyHandler;
import mekanism.common.registration.impl.ItemDeferredRegister;
import mekanism.common.util.text.TextComponentUtil;
import mekanism.common.util.text.Translation;
import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ItemBlockTooltip<BLOCK extends Block> extends ItemBlockMekanism<BLOCK> {

    public ItemBlockTooltip(BLOCK block) {
        this(block, ItemDeferredRegister.getMekBaseProperties());
    }

    public ItemBlockTooltip(BLOCK block, Item.Properties properties) {
        super(block, properties);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(@Nonnull ItemStack itemstack, World world, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flag) {
        if (!MekKeyHandler.getIsKeyPressed(MekanismKeyHandler.sneakKey)) {
            addStats(itemstack, world, tooltip, flag);
            tooltip.add(TextComponentUtil.build(Translation.of("tooltip.mekanism.hold"), " ", EnumColor.INDIGO, MekanismKeyHandler.sneakKey.getKey(),
                  EnumColor.GRAY, " ", Translation.of("tooltip.mekanism.for_details"), "."));
        } else {
            addDescription(itemstack, world, tooltip, flag);
        }
    }

    @OnlyIn(Dist.CLIENT)
    public void addStats(@Nonnull ItemStack itemstack, World world, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flag) {
    }

    @OnlyIn(Dist.CLIENT)
    public void addDescription(@Nonnull ItemStack itemstack, World world, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flag) {
        //TODO: Previously had a max width thing, is this needed or does vanilla handle it
        tooltip.add(TextComponentUtil.translate("tooltip.mekanism." + getRegistryName().getPath()));
    }
}