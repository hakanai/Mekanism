package mekanism.tools.common.item;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import mekanism.common.Mekanism;
import mekanism.common.util.text.TextComponentUtil;
import mekanism.common.util.text.Translation;
import mekanism.tools.common.IHasRepairType;
import mekanism.tools.common.MekanismTools;
import mekanism.tools.common.material.IMekanismMaterial;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTier;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.ToolItem;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;

public class ItemMekanismPaxel extends ToolItem implements IHasRepairType {

    public ItemMekanismPaxel(IMekanismMaterial material) {
        super(material.getPaxelDamage(), material.getPaxelAtkSpeed(), material, new HashSet<>(), new Item.Properties().group(Mekanism.tabMekanism)
              .addToolType(ToolType.AXE, material.getPaxelHarvestLevel()).addToolType(ToolType.PICKAXE, material.getPaxelHarvestLevel())
              .addToolType(ToolType.SHOVEL, material.getPaxelHarvestLevel()));
        setRegistryName(new ResourceLocation(MekanismTools.MODID, material.getRegistryPrefix() + "_paxel"));
    }

    public ItemMekanismPaxel(ItemTier material) {
        super(4, -2.4F, material, new HashSet<>(), new Item.Properties().group(Mekanism.tabMekanism)
              .addToolType(ToolType.AXE, material.getHarvestLevel()).addToolType(ToolType.PICKAXE, material.getHarvestLevel())
              .addToolType(ToolType.SHOVEL, material.getHarvestLevel()));
        setRegistryName(new ResourceLocation(MekanismTools.MODID, material.name().toLowerCase(Locale.ROOT) + "_paxel"));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        tooltip.add(TextComponentUtil.build(Translation.of("mekanism.tooltip.hp"), ": " + (stack.getMaxDamage() - stack.getDamage())));
    }

    @Override
    public float getDestroySpeed(@Nonnull ItemStack stack, BlockState state) {
        Material material = state.getMaterial();
        boolean pickaxeShortcut = material == Material.IRON || material == Material.ANVIL || material == Material.ROCK;
        boolean axeShortcut = material == Material.WOOD || material == Material.PLANTS || material == Material.TALL_PLANTS || material == Material.BAMBOO;
        return pickaxeShortcut || axeShortcut ? this.efficiency : super.getDestroySpeed(stack, state);
    }

    @Nonnull
    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        //Copied from AxeItem#onItemUse
        World world = context.getWorld();
        BlockPos blockpos = context.getPos();
        BlockState blockstate = world.getBlockState(blockpos);
        Block block = AxeItem.BLOCK_STRIPPING_MAP.get(blockstate.getBlock());
        if (block == null) {
            return ActionResultType.PASS;
        }
        PlayerEntity playerentity = context.getPlayer();
        world.playSound(playerentity, blockpos, SoundEvents.ITEM_AXE_STRIP, SoundCategory.BLOCKS, 1.0F, 1.0F);
        if (!world.isRemote) {
            world.setBlockState(blockpos, block.getDefaultState().with(RotatedPillarBlock.AXIS, blockstate.get(RotatedPillarBlock.AXIS)), 11);
            if (playerentity != null) {
                context.getItem().damageItem(1, playerentity, onBroken -> onBroken.sendBreakAnimation(context.getHand()));
            }
        }
        return ActionResultType.SUCCESS;
    }

    @Nonnull
    @Override
    public Ingredient getRepairMaterial() {
        return getTier().getRepairMaterial();
    }
}