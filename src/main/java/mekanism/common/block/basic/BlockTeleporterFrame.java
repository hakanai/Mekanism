package mekanism.common.block.basic;

import mekanism.api.block.IHasModel;
import mekanism.common.block.BlockTileDrops;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockTeleporterFrame extends BlockTileDrops implements IHasModel {

    public BlockTeleporterFrame() {
        super(Block.Properties.create(Material.IRON).hardnessAndResistance(5F, 10F).lightValue(12));
    }
}