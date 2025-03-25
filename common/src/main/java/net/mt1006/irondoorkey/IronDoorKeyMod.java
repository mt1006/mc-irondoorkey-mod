package net.mt1006.irondoorkey;

import com.mojang.logging.LogUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import org.slf4j.Logger;

public class IronDoorKeyMod
{
	public static final String MOD_ID = "irondoorkey";
	public static final Logger LOGGER = LogUtils.getLogger();

	public static final TagKey<Block> OPENABLE = TagKey.create(Registries.BLOCK, new ResourceLocation(MOD_ID, "openable"));
}
