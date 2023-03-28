package net.roopert.terror.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.roopert.terror.sounds.ModSounds;

public class Sirenium extends FlowerBlock {
    public Sirenium() {
        super(MobEffects.DARKNESS, 5, BlockBehaviour.Properties.copy(Blocks.DANDELION));
    }

    public void animateTick(BlockState p_221794_, Level p_221795_, BlockPos p_221796_, RandomSource p_221797_) {
        if (p_221797_.nextInt(20) == 0) {
            double x = p_221796_.getX();
            double y = p_221796_.getY();
            double z = p_221796_.getZ();
            int distance = 30;
            float volume = 0.5F;
            float pitch = p_221797_.nextFloat() * 0.4F + 0.8F;

            // If the block is within 5 blocks, keep the volume the same.
            // Otherwise, decrease the volume as the distance increases.
            if (distance > 5) {
                volume = (float)(volume / ( Math.pow(distance, 2) / Math.pow(5, 2) ));
            }

            p_221795_.playLocalSound(x + 0.5D, y + 0.5D, z + 0.5D, ModSounds.SIRENIUM_SCREAM1.get(), SoundSource.BLOCKS, volume, pitch, false);
            p_221795_.addParticle(ParticleTypes.SOUL, x + 0.5D, y + 0.5D, z + 0.5D, 0.0D, 0.2D, 0.0D);
            SoundEvent soundEvent = ModSounds.SIRENIUM_SCREAM1.get();

        }
    }
}