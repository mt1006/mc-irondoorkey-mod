package com.mt1006.irondoorkey;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
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

		if (!blockState.is(IronDoorKeyCommon.OPENABLE)) { return InteractionResult.PASS; }
		Block blockType = blockState.getBlock();

		if (blockType instanceof DoorBlock)
		{
			DoorBlock doorBlock = (DoorBlock)blockType;
			doorBlock.setOpen(ctx.getPlayer(), level, blockState, blockPos, !doorBlock.isOpen(blockState));
			return InteractionResult.sidedSuccess(level.isClientSide);
		}
		else if (blockType instanceof TrapDoorBlock)
		{
			openTrapDoor(ctx.getPlayer(), level, blockPos, blockState);
			return InteractionResult.sidedSuccess(level.isClientSide);
		}
		else if (blockType instanceof FenceGateBlock)
		{
			// redundant in vanilla, added for better mod support, e.g. with SecurityCraft
			openFenceGate(ctx.getPlayer(), level, blockPos, blockState, (FenceGateBlock)blockType);
			return InteractionResult.sidedSuccess(level.isClientSide);
		}

		IronDoorKeyCommon.LOGGER.warn("Failed to open the block - " +
				"it has \"openable\" tag, but isn't instance of DoorBlock, TrapDoorBlock or FenceGateBlock");
		return InteractionResult.PASS;
	}

	private static void openTrapDoor(@Nullable Player player, Level level, BlockPos blockPos, BlockState blockState)
	{
		BlockState newBlockState = blockState.cycle(TrapDoorBlock.OPEN);
		level.setBlock(blockPos, newBlockState, 2);

		if (blockState.getValue(TrapDoorBlock.WATERLOGGED))
		{
			level.scheduleTick(blockPos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
		}

		boolean isOpen = newBlockState.getValue(TrapDoorBlock.OPEN);
		level.levelEvent(player, isOpen ? 1037 : 1036, blockPos, 0);
		level.gameEvent(player, isOpen ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, blockPos);
	}

	private static void openFenceGate(@Nullable Player player, Level level, BlockPos blockPos, BlockState blockState, FenceGateBlock blockType)
	{
		boolean wasOpen = blockState.getValue(FenceGateBlock.OPEN);
		if (wasOpen)
		{
			BlockState newBlockState = blockState.setValue(FenceGateBlock.OPEN, false);
			level.setBlock(blockPos, newBlockState, 10);
		}
		else
		{
			BlockState newBlockState = blockState.setValue(FenceGateBlock.OPEN, true);
			if (player != null && player.getDirection().getOpposite() == blockState.getValue(FenceGateBlock.FACING))
			{
				newBlockState = newBlockState.setValue(FenceGateBlock.FACING, player.getDirection());
			}
			level.setBlock(blockPos, newBlockState, 10);
		}

		boolean isOpen = !wasOpen;
		level.levelEvent(player, isOpen ? 1008 : 1014, blockPos, 0);
		level.gameEvent(player, isOpen ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, blockPos);
	}
}
