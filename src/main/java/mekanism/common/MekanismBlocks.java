package mekanism.common;

import static mekanism.common.block.states.BlockStateBasic.BasicBlock.BASIC_BLOCK_1;
import static mekanism.common.block.states.BlockStateBasic.BasicBlock.BASIC_BLOCK_2;
import static mekanism.common.block.states.BlockStateMachine.MachineBlock.MACHINE_BLOCK_1;
import static mekanism.common.block.states.BlockStateMachine.MachineBlock.MACHINE_BLOCK_2;
import static mekanism.common.block.states.BlockStateMachine.MachineBlock.MACHINE_BLOCK_3;
import mekanism.api.EnumColor;
import mekanism.common.Tier.EnergyCubeTier;
import mekanism.common.Tier.GasTankTier;
import mekanism.common.base.IEnergyCube;
import mekanism.common.block.BlockBasic;
import mekanism.common.block.BlockBounding;
import mekanism.common.block.BlockCardboardBox;
import mekanism.common.block.BlockEnergyCube;
import mekanism.common.block.BlockGasTank;
import mekanism.common.block.BlockMachine;
import mekanism.common.block.BlockObsidianTNT;
import mekanism.common.block.BlockOre;
import mekanism.common.block.BlockPlastic;
import mekanism.common.block.BlockPlasticFence;
import mekanism.common.block.BlockPlasticFence.PlasticFenceStateMapper;
import mekanism.common.block.BlockSalt;
import mekanism.common.block.states.BlockStateBasic.BasicBlockStateMapper;
import mekanism.common.block.states.BlockStateBasic.BasicBlockType;
import mekanism.common.block.states.BlockStateCardboardBox.CardboardBoxStateMapper;
import mekanism.common.block.states.BlockStateMachine.MachineBlockStateMapper;
import mekanism.common.block.states.BlockStateMachine.MachineType;
import mekanism.common.block.states.BlockStateOre.EnumOreType;
import mekanism.common.block.states.BlockStatePlastic.PlasticBlockStateMapper;
import mekanism.common.block.states.BlockStatePlastic.PlasticBlockType;
import mekanism.common.item.ItemBlockBasic;
import mekanism.common.item.ItemBlockCardboardBox;
import mekanism.common.item.ItemBlockEnergyCube;
import mekanism.common.item.ItemBlockGasTank;
import mekanism.common.item.ItemBlockMachine;
import mekanism.common.item.ItemBlockOre;
import mekanism.common.item.ItemBlockPlastic;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

@ObjectHolder("Mekanism")
public class MekanismBlocks
{
	public static final Block BasicBlock = BlockBasic.getBlockBasic(BASIC_BLOCK_1).setUnlocalizedName("BasicBlock");
	public static final Block BasicBlock2 = BlockBasic.getBlockBasic(BASIC_BLOCK_2).setUnlocalizedName("BasicBlock2");
	public static final Block MachineBlock = BlockMachine.getBlockMachine(MACHINE_BLOCK_1).setUnlocalizedName("MachineBlock");
	public static final Block MachineBlock2 = BlockMachine.getBlockMachine(MACHINE_BLOCK_2).setUnlocalizedName("MachineBlock2");
	public static final Block MachineBlock3 = BlockMachine.getBlockMachine(MACHINE_BLOCK_3).setUnlocalizedName("MachineBlock3");
	public static final Block OreBlock = new BlockOre().setUnlocalizedName("OreBlock");
	public static final Block ObsidianTNT = new BlockObsidianTNT().setUnlocalizedName("ObsidianTNT").setCreativeTab(Mekanism.tabMekanism);
	public static final Block EnergyCube = new BlockEnergyCube().setUnlocalizedName("EnergyCube");
	public static final Block BoundingBlock = (BlockBounding)new BlockBounding().setUnlocalizedName("BoundingBlock");
	public static final Block GasTank = new BlockGasTank().setUnlocalizedName("GasTank");
	public static final Block CardboardBox = new BlockCardboardBox().setUnlocalizedName("CardboardBox");
	public static final Block PlasticBlock = new BlockPlastic(PlasticBlockType.PLASTIC).setUnlocalizedName("PlasticBlock");
	public static final Block SlickPlasticBlock = new BlockPlastic(PlasticBlockType.SLICK).setUnlocalizedName("SlickPlasticBlock");
	public static final Block GlowPlasticBlock = new BlockPlastic(PlasticBlockType.GLOW).setUnlocalizedName("GlowPlasticBlock");
	public static final Block ReinforcedPlasticBlock = new BlockPlastic(PlasticBlockType.REINFORCED).setUnlocalizedName("ReinforcedPlasticBlock");
	public static final Block RoadPlasticBlock = new BlockPlastic(PlasticBlockType.ROAD).setUnlocalizedName("RoadPlasticBlock");
	public static final Block PlasticFence = new BlockPlasticFence().setUnlocalizedName("PlasticFence");
	public static final Block SaltBlock = new BlockSalt().setUnlocalizedName("SaltBlock");

	private static final IStateMapper machineMapper = new MachineBlockStateMapper();
	private static final IStateMapper basicMapper = new BasicBlockStateMapper();
	private static final IStateMapper plasticMapper = new PlasticBlockStateMapper();
	private static final IStateMapper fenceMapper = new PlasticFenceStateMapper();
	private static final IStateMapper boxMapper = new CardboardBoxStateMapper();

	/**
	 * Adds and registers all blocks.
	 */
	public static void register()
	{
		GameRegistry.registerBlock(BasicBlock, ItemBlockBasic.class, "BasicBlock");
		GameRegistry.registerBlock(BasicBlock2, ItemBlockBasic.class, "BasicBlock2");
		GameRegistry.registerBlock(MachineBlock, ItemBlockMachine.class, "MachineBlock");
		GameRegistry.registerBlock(MachineBlock2, ItemBlockMachine.class, "MachineBlock2");
		GameRegistry.registerBlock(MachineBlock3, ItemBlockMachine.class, "MachineBlock3");
		GameRegistry.registerBlock(OreBlock, ItemBlockOre.class, "OreBlock");
		GameRegistry.registerBlock(EnergyCube, ItemBlockEnergyCube.class, "EnergyCube");
		GameRegistry.registerBlock(ObsidianTNT, "ObsidianTNT");
		GameRegistry.registerBlock(BoundingBlock, "BoundingBlock");
		GameRegistry.registerBlock(GasTank, ItemBlockGasTank.class, "GasTank");
		GameRegistry.registerBlock(CardboardBox, ItemBlockCardboardBox.class, "CardboardBox");
		GameRegistry.registerBlock(PlasticBlock, ItemBlockPlastic.class, "PlasticBlock");
		GameRegistry.registerBlock(SlickPlasticBlock, ItemBlockPlastic.class, "SlickPlasticBlock");
		GameRegistry.registerBlock(GlowPlasticBlock, ItemBlockPlastic.class, "GlowPlasticBlock");
		GameRegistry.registerBlock(ReinforcedPlasticBlock, ItemBlockPlastic.class, "ReinforcedPlasticBlock");
		GameRegistry.registerBlock(RoadPlasticBlock, ItemBlockPlastic.class, "RoadPlasticBlock");
		GameRegistry.registerBlock(PlasticFence, ItemBlockPlastic.class, "PlasticFence");
		GameRegistry.registerBlock(SaltBlock, "SaltBlock");

		ModelLoader.setCustomStateMapper(MachineBlock, machineMapper);
		ModelLoader.setCustomStateMapper(MachineBlock2, machineMapper);
		ModelLoader.setCustomStateMapper(MachineBlock3, machineMapper);
		ModelLoader.setCustomStateMapper(BasicBlock, basicMapper);
		ModelLoader.setCustomStateMapper(BasicBlock2, basicMapper);
		ModelLoader.setCustomStateMapper(PlasticBlock, plasticMapper);
		ModelLoader.setCustomStateMapper(SlickPlasticBlock, plasticMapper);
		ModelLoader.setCustomStateMapper(GlowPlasticBlock, plasticMapper);
		ModelLoader.setCustomStateMapper(ReinforcedPlasticBlock, plasticMapper);
		ModelLoader.setCustomStateMapper(RoadPlasticBlock, plasticMapper);
		ModelLoader.setCustomStateMapper(PlasticFence, fenceMapper);
		ModelLoader.setCustomStateMapper(CardboardBox, boxMapper);
		
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(MekanismBlocks.ObsidianTNT), 0, new ModelResourceLocation("mekanism:ObsidianTNT", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(MekanismBlocks.SaltBlock), 0, new ModelResourceLocation("mekanism:SaltBlock", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(MekanismBlocks.CardboardBox), 0, new ModelResourceLocation("mekanism:CardboardBox", "storage=false"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(MekanismBlocks.CardboardBox), 1, new ModelResourceLocation("mekanism:CardboardBox", "storage=true"));

		for(MachineType type : MachineType.values())
		{
			ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(type.typeBlock.getBlock()), type.meta, new ModelResourceLocation("mekanism:" + type.getName(), (type.hasActiveTexture() ? "active=false," : "") + "facing=north"));
		}

		for(BasicBlockType type : BasicBlockType.values())
		{
			ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(type.blockType.getBlock()), type.meta, new ModelResourceLocation("mekanism:" + type.getName(), "inventory"));
		}

		for(EnumColor color : EnumColor.DYES)
		{
			ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(MekanismBlocks.PlasticBlock), color.getMetaValue(), new ModelResourceLocation("mekanism:plastic_block", "type=plastic"));
			ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(MekanismBlocks.SlickPlasticBlock), color.getMetaValue(), new ModelResourceLocation("mekanism:plastic_block", "type=slick"));
			ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(MekanismBlocks.GlowPlasticBlock), color.getMetaValue(), new ModelResourceLocation("mekanism:plastic_block", "type=glow"));
			ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(MekanismBlocks.ReinforcedPlasticBlock), color.getMetaValue(), new ModelResourceLocation("mekanism:plastic_block", "type=reinforced"));
			ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(MekanismBlocks.RoadPlasticBlock), color.getMetaValue(), new ModelResourceLocation("mekanism:plastic_block", "type=road"));
			ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(MekanismBlocks.PlasticFence), color.getMetaValue(), new ModelResourceLocation("mekanism:PlasticFence", "inventory"));
		}

		for(EnumOreType ore : EnumOreType.values())
		{
			ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(MekanismBlocks.OreBlock), ore.ordinal(), new ModelResourceLocation("mekanism:OreBlock", "type=" + ore.getName()));
		}

		for(GasTankTier tier : GasTankTier.values())
		{
			ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(MekanismBlocks.GasTank), tier.ordinal(), new ModelResourceLocation("mekanism:GasTank", "inventory")); //TODO tier states
		}

		ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(MekanismBlocks.EnergyCube), new ItemMeshDefinition() {
			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack)
			{
				EnergyCubeTier tier = ((IEnergyCube)stack.getItem()).getEnergyCubeTier(stack);
				ResourceLocation baseLocation = new ResourceLocation("mekanism", "EnergyCube");
				
				return new ModelResourceLocation(baseLocation, "facing=north,tier="+tier);
			}
		});
	}
}
