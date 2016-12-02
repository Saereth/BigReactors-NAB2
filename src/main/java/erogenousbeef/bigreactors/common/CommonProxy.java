package erogenousbeef.bigreactors.common;

import erogenousbeef.bigreactors.api.registry.Reactants;
import erogenousbeef.bigreactors.common.block.BlockBR;
import erogenousbeef.bigreactors.common.block.BlockBRGenericFluid;
import erogenousbeef.bigreactors.common.data.StandardReactants;
import erogenousbeef.bigreactors.common.item.ItemBase;
import erogenousbeef.bigreactors.init.BrItems;
import erogenousbeef.bigreactors.utils.intermod.ModHelperBase;
import erogenousbeef.bigreactors.utils.intermod.ModHelperComputerCraft;
import erogenousbeef.bigreactors.utils.intermod.ModHelperMekanism;
import it.zerono.mods.zerocore.lib.IModInitializationHandler;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CommonProxy implements IModInitializationHandler {

	public BlockBR register(BlockBR block) {

		GameRegistry.register(block);
		block.setCreativeTab(BigReactors.TAB);
		block.onPostRegister();
		return block;
	}

	public BlockBRGenericFluid register(BlockBRGenericFluid block) {

		GameRegistry.register(block);
		block.setCreativeTab(BigReactors.TAB);
		block.onPostRegister();
		return block;
	}

	public ItemBase register(ItemBase item) {

		GameRegistry.register(item);
		item.setCreativeTab(BigReactors.TAB);
		item.onPostRegister();
		return item;
	}

	public void register(Class<? extends TileEntity> tileEntityClass) {

		GameRegistry.registerTileEntity(tileEntityClass, BigReactors.MODID + tileEntityClass.getSimpleName());
	}

	@Override
	public void onPreInit(FMLPreInitializationEvent event) {

	}

	@Override
	public void onInit(FMLInitializationEvent event) {
		sendInterModAPIMessages();

	}

	@Override
	public void onPostInit(FMLPostInitializationEvent event) {

		if (BigReactors.CONFIG.autoAddUranium)
			Reactants.registerSolid("ingotUranium", StandardReactants.yellorium);
		Reactants.registerSolid("uFuel", StandardReactants.uFuel);
		this.registerWithOtherMods();
	}

	private void sendInterModAPIMessages() {


		MetalType[] metals = MetalType.values();
		int length = metals.length;
		ItemStack[] ingots = new ItemStack[length];
		ItemStack[] dusts = new ItemStack[length];
		
		for(int i = 0; i < length; ++i) {

			ingots[i] = BrItems.ingotMetals.createItemStack(metals[i], 1);
			dusts[i] = BrItems.dustMetals.createItemStack(metals[i], 1);
		}

		
		 // END: IsModLoaded - AE2
	}


	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void registerIcons(TextureStitchEvent.Pre event) {
	}
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void setIcons(TextureStitchEvent.Post event) {
	}
	
	/// Mod Interoperability ///
	void registerWithOtherMods() {
		ModHelperBase modHelper;
		
		ModHelperBase.detectMods();

		modHelper = new ModHelperComputerCraft();
		modHelper.register();
		
		modHelper = new ModHelperMekanism();
		modHelper.register();
	}
}
