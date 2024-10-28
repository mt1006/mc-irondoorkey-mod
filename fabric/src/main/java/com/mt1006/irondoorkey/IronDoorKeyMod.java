package com.mt1006.irondoorkey;

import net.fabricmc.api.ModInitializer;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class IronDoorKeyMod implements ModInitializer
{
	private static final Item ITEM_IRON_DOOR_KEY = new IronDoorKeyItem();

	@Override public void onInitialize()
	{
		Registry.register(Registry.ITEM, new ResourceLocation(IronDoorKeyCommon.MOD_ID, "iron_door_key"), ITEM_IRON_DOOR_KEY);
	}
}
