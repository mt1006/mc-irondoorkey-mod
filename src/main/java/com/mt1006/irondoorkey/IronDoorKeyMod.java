package com.mt1006.irondoorkey;

import com.mojang.logging.LogUtils;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.slf4j.Logger;

@Mod(IronDoorKeyMod.MOD_ID)
public class IronDoorKeyMod
{
	public static final String MOD_ID = "irondoorkey";
	public static final String VERSION = "1.1";
	public static final String FOR_VERSION = "1.20.6";
	public static final String FOR_LOADER = "NeoForge";
	public static final Logger LOGGER = LogUtils.getLogger();

	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(BuiltInRegistries.ITEM, MOD_ID);
	public static final DeferredHolder<Item, IronDoorKeyItem> ITEM_IRON_DOOR_KEY = ITEMS.register("iron_door_key", IronDoorKeyItem::new);
	public static final TagKey<Block> OPENABLE = TagKey.create(Registries.BLOCK, new ResourceLocation(MOD_ID, "openable"));

	public IronDoorKeyMod(IEventBus eventBus)
	{
		LOGGER.info("{} - Author: mt1006", getFullName());
		ITEMS.register(eventBus);
	}

	public static String getFullName()
	{
		return "IronDoorKey v" + VERSION + " for Minecraft " + FOR_VERSION + " [" + FOR_LOADER + "]";
	}
}
