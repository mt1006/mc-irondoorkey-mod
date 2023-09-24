package com.mt1006.irondoorkey;

import com.mojang.logging.LogUtils;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

@Mod(IronDoorKeyMod.MOD_ID)
@Mod.EventBusSubscriber(modid = IronDoorKeyMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class IronDoorKeyMod
{
	public static final String MOD_ID = "irondoorkey";
	public static final String VERSION = "1.0";
	public static final String FOR_VERSION = "1.20.2";
	public static final String FOR_LOADER = "Forge";
	public static final Logger LOGGER = LogUtils.getLogger();

	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, IronDoorKeyMod.MOD_ID);
	public static final RegistryObject<Item> ITEM_IRON_DOOR_KEY = ITEMS.register("iron_door_key", IronDoorKeyItem::new);

	public IronDoorKeyMod()
	{
		MinecraftForge.EVENT_BUS.register(this);

		IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
		ITEMS.register(eventBus);
	}

	@SubscribeEvent
	public static void setup(final FMLCommonSetupEvent event)
	{
		LOGGER.info(getFullName() + " - Author: mt1006 (mt1006x)");
	}

	public static String getFullName()
	{
		return "IronDoorKey v" + VERSION + " for Minecraft " + FOR_VERSION + " [" + FOR_LOADER + "]";
	}
}
