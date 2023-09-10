package com.mt1006.irondoorkey;

import com.mojang.logging.LogUtils;
import net.fabricmc.api.ModInitializer;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import org.slf4j.Logger;

public class IronDoorKeyMod implements ModInitializer
{
	public static final String MOD_ID = "irondoorkey";
	public static final String VERSION = "1.0";
	public static final String FOR_VERSION = "1.19.2";
	public static final String FOR_LOADER = "Fabric";
	public static final Logger LOGGER = LogUtils.getLogger();

	public static final Item ITEM_IRON_DOOR_KEY = new IronDoorKeyItem();

	@Override public void onInitialize()
	{
		LOGGER.info(getFullName() + " - Author: mt1006 (mt1006x)");
		Registry.register(Registry.ITEM, new ResourceLocation(MOD_ID, "iron_door_key"), ITEM_IRON_DOOR_KEY);
	}

	public static String getFullName()
	{
		return "IronDoorKey v" + VERSION + " for Minecraft " + FOR_VERSION + " [" + FOR_LOADER + "]";
	}
}
