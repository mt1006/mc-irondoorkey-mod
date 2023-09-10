package com.mt1006.irondoorkey;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class IronDoorKeyItem extends Item
{
	public IronDoorKeyItem()
	{
		super(new Item.Properties().tab(ItemGroup.TAB_TOOLS));
	}

	@Override public ActionResultType useOn(ItemUseContext ctx)
	{
		World level = ctx.getLevel();
		BlockPos blockPos = ctx.getClickedPos();
		BlockState blockState = level.getBlockState(blockPos);
		Block blockType = blockState.getBlock();

		if (blockType instanceof DoorBlock)
		{
			if (blockType == Blocks.IRON_DOOR)
			{
				openDoor(ctx.getPlayer(), level, blockPos, blockState);
				return ActionResultType.sidedSuccess(level.isClientSide);
			}
		}
		else if (blockType instanceof TrapDoorBlock)
		{
			if (blockType == Blocks.IRON_TRAPDOOR)
			{
				openTrapDoor(ctx.getPlayer(), level, blockPos, blockState);
				return ActionResultType.sidedSuccess(level.isClientSide);
			}
		}
		return ActionResultType.PASS;
	}

	private static void openDoor(@Nullable PlayerEntity player, World level, BlockPos blockPos, BlockState blockState)
	{
		BlockState newBlockState = blockState.cycle(DoorBlock.OPEN);
		level.setBlock(blockPos, newBlockState, 2);
		level.levelEvent(player, newBlockState.getValue(DoorBlock.OPEN) ? 1005 : 1011, blockPos, 0);
	}

	private static void openTrapDoor(@Nullable PlayerEntity player, World level, BlockPos blockPos, BlockState blockState)
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
