package com.mt1006.irondoorkey;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class IronDoorKeyItem extends Item
{
	public IronDoorKeyItem()
	{
		super(new Item.Properties().tab(CreativeModeTab.TAB_TOOLS));
	}

	@Override public @NotNull InteractionResult useOn(@NotNull UseOnContext ctx)
	{
		Level level = ctx.getLevel();
		BlockPos blockPos = ctx.getClickedPos();
		BlockState blockState = level.getBlockState(blockPos);
		Block blockType = blockState.getBlock();

		if (blockType instanceof DoorBlock)
		{
			if (blockType == Blocks.IRON_DOOR)
			{
				openDoor(ctx.getPlayer(), level, blockPos, blockState);
				return InteractionResult.sidedSuccess(level.isClientSide);
			}
		}
		else if (blockType instanceof TrapDoorBlock)
		{
			if (blockType == Blocks.IRON_TRAPDOOR)
			{
				openTrapDoor(ctx.getPlayer(), level, blockPos, blockState);
				return InteractionResult.sidedSuccess(level.isClientSide);
			}
		}
		return InteractionResult.PASS;
	}

	private static void openDoor(@Nullable Player player, Level level, BlockPos blockPos, BlockState blockState)
	{
		BlockState newBlockState = blockState.cycle(DoorBlock.OPEN);
		level.setBlock(blockPos, newBlockState, 2);
		level.levelEvent(player, newBlockState.getValue(DoorBlock.OPEN) ? 1005 : 1011, blockPos, 0);
	}

	private static void openTrapDoor(@Nullable Player player, Level level, BlockPos blockPos, BlockState blockState)
	{
		BlockState newBlockState = blockState.cycle(TrapDoorBlock.OPEN);
		level.setBlock(blockPos, newBlockState, 2);

		if (blockState.getValue(TrapDoorBlock.WATERLOGGED))
		{
			level.getLiquidTicks().scheduleTick(blockPos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
		}

		level.levelEvent(player, newBlockState.getValue(TrapDoorBlock.OPEN) ? 1037 : 1036, blockPos, 0);
	}
}
