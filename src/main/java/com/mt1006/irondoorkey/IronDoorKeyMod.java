package com.mt1006.irondoorkey;

import com.mojang.logging.LogUtils;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

@Mod(IronDoorKeyMod.MOD_ID)
public class IronDoorKeyMod
{
	public static final String MOD_ID = "irondoorkey";
	public static final String VERSION = "1.1";
	public static final String FOR_VERSION = "1.19.2";
	public static final String FOR_LOADER = "Forge";
	public static final Logger LOGGER = LogUtils.getLogger();

	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, IronDoorKeyMod.MOD_ID);
	public static final RegistryObject<Item> ITEM_IRON_DOOR_KEY = ITEMS.register("iron_door_key", IronDoorKeyItem::new);
	public static final TagKey<Block> OPENABLE = TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation(MOD_ID, "openable"));

	public IronDoorKeyMod()
	{
		LOGGER.info("{} - Author: mt1006", getFullName());
		IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
		ITEMS.register(eventBus);
	}

	public static String getFullName()
	{
		return "IronDoorKey v" + VERSION + " for Minecraft " + FOR_VERSION + " [" + FOR_LOADER + "]";
	}
}
