package com.mt1006.irondoorkey;

import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = IronDoorKeyMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents
{
	@SubscribeEvent
	public static void creativeModeTabSetup(CreativeModeTabEvent.BuildContents event)
	{
		if (event.getTab().equals(CreativeModeTabs.TOOLS_AND_UTILITIES))
		{
			event.accept(IronDoorKeyMod.ITEM_IRON_DOOR_KEY);
		}
	}
}