package com.mt1006.irondoorkey;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluids;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class IronDoorKeyItem extends Item
{
	public IronDoorKeyItem()
	{
		super(new Item.Properties());
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
				DoorBlock doorBlock = (DoorBlock)blockType;
				doorBlock.setOpen(ctx.getPlayer(), level, blockState, blockPos, !doorBlock.isOpen(blockState));
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

	private static void openTrapDoor(@Nullable Player player, Level level, BlockPos blockPos, BlockState blockState)
	{
		BlockState newBlockState = blockState.cycle(TrapDoorBlock.OPEN);
		level.setBlock(blockPos, newBlockState, 2);

		if (blockState.getValue(TrapDoorBlock.WATERLOGGED))
		{
			level.scheduleTick(blockPos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
		}

		boolean isOpen = newBlockState.getValue(TrapDoorBlock.OPEN);
		level.playSound(player, blockPos, isOpen ? BlockSetType.IRON.trapdoorOpen() : BlockSetType.IRON.trapdoorClose(),
				SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.1F + 0.9F);
		level.gameEvent(player, isOpen ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, blockPos);
	}
}
