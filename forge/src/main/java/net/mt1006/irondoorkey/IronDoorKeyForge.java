package net.mt1006.irondoorkey;

import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod(IronDoorKeyMod.MOD_ID)
public class IronDoorKeyForge
{
	private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, IronDoorKeyMod.MOD_ID);
	private static final RegistryObject<Item> ITEM_IRON_DOOR_KEY = ITEMS.register("iron_door_key", IronDoorKeyItem::new);

	public IronDoorKeyForge(FMLJavaModLoadingContext context)
	{
		IEventBus eventBus = context.getModEventBus();
		ITEMS.register(eventBus);
		eventBus.addListener(this::creativeModeTabSetup);
	}

	private void creativeModeTabSetup(BuildCreativeModeTabContentsEvent event)
	{
		if (event.getTabKey().equals(CreativeModeTabs.TOOLS_AND_UTILITIES))
		{
			event.accept(ITEM_IRON_DOOR_KEY);
		}
	}
}
