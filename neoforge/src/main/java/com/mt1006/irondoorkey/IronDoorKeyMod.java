package com.mt1006.irondoorkey;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@Mod(IronDoorKeyCommon.MOD_ID)
public class IronDoorKeyMod
{
	private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(BuiltInRegistries.ITEM, IronDoorKeyCommon.MOD_ID);
	private static final DeferredHolder<Item, IronDoorKeyItem> ITEM_IRON_DOOR_KEY = ITEMS.register("iron_door_key", IronDoorKeyItem::new);

	public IronDoorKeyMod(IEventBus eventBus)
	{
		ITEMS.register(eventBus);
		eventBus.addListener(this::creativeModeTabSetup);
	}

	private void creativeModeTabSetup(BuildCreativeModeTabContentsEvent event)
	{
		if (event.getTabKey().equals(CreativeModeTabs.TOOLS_AND_UTILITIES))
		{
			event.accept(IronDoorKeyMod.ITEM_IRON_DOOR_KEY.get());
		}
	}
}
