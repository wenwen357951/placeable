package it.bisumto.placeable.mixin;

import it.bisumto.placeable.Placeable;
import net.minecraft.block.BlockState;
import net.minecraft.block.SmallDripleafBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SmallDripleafBlock.class)
public class SmallDripleafBlockMixin {

    // PLACEABLE
    @Inject(method = "canPlaceAt", at = @At("HEAD"), cancellable = true)
    public void canPlantAnywhere(BlockState blockState, WorldView world, BlockPos blockPos, CallbackInfoReturnable<Boolean> cir) {
        if (Placeable.isValidFloor(world, blockPos)) {
            cir.setReturnValue(true);
        }
    }
}
