package com.mt1006.irondoorkey;

import com.mojang.logging.LogUtils;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import org.slf4j.Logger;

public class IronDoorKeyMod implements ModInitializer
{
	public static final String MOD_ID = "irondoorkey";
	public static final String VERSION = "1.0";
	public static final String FOR_VERSION = "1.20.1";
	public static final String FOR_LOADER = "Fabric";
	public static final Logger LOGGER = LogUtils.getLogger();

	public static final Item ITEM_IRON_DOOR_KEY = new IronDoorKeyItem();

	@Override public void onInitialize()
	{
		LOGGER.info(getFullName() + " - Author: mt1006 (mt1006x)");
		Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(MOD_ID, "iron_door_key"), ITEM_IRON_DOOR_KEY);

		ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES)
				.register(content -> content.accept(ITEM_IRON_DOOR_KEY));
	}

	public static String getFullName()
	{
		return "IronDoorKey v" + VERSION + " for Minecraft " + FOR_VERSION + " [" + FOR_LOADER + "]";
	}
}
