package it.bisumto.placeable.mixin;

import net.minecraft.block.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.minecraft.block.HorizontalFacingBlock.FACING;

@Mixin(CocoaBlock.class)
public class CocoaBlockMixin {

    // PLACEABLE
    @Inject(method = "canPlaceAt", at = @At("HEAD"), cancellable = true)
    public void canPlantAnywhere(BlockState state, WorldView world, BlockPos pos, CallbackInfoReturnable<Boolean> cir){
        BlockState blockState = world.getBlockState(pos.offset(state.get(FACING)));
        if(blockState.isSideSolid(world, pos, state.get(FACING), SideShapeType.RIGID))
            cir.setReturnValue(true);
    }

    // PREVENT GROWING
    @Inject(method = "randomTick", at = @At("HEAD"), cancellable = true)
    public void randomTickMixin(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {
        BlockState floor = world.getBlockState(pos.offset(state.get(FACING)));
        if (!floor.isIn(BlockTags.JUNGLE_LOGS))
            ci.cancel();
    }

}
