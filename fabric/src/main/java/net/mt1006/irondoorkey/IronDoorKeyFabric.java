package net.mt1006.irondoorkey;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;

public class IronDoorKeyFabric implements ModInitializer
{
	private static final Item ITEM_IRON_DOOR_KEY = new IronDoorKeyItem();

	@Override public void onInitialize()
	{
		Registry.register(BuiltInRegistries.ITEM, IronDoorKeyItem.ID, ITEM_IRON_DOOR_KEY);
		CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register((content) -> content.accept(ITEM_IRON_DOOR_KEY));
	}
}
