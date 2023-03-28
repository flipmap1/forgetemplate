package net.roopert.terror.effect;

import net.minecraft.client.OptionInstance;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class FlashlightEffect extends MobEffect {

    public FlashlightEffect() {

        super(MobEffectCategory.BENEFICIAL, 0x0000BB);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;

    }
    // Override the applyEffectTick method to modify the player's gamma
    @OnlyIn(Dist.CLIENT)
    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity.level.isClientSide) {
            Minecraft minecraft = Minecraft.getInstance();
            // Increase the gamma of the player's screen by 1.0
            // Get the OptionInstance<Double> object for the gamma option
            OptionInstance<Double> gammaOption = minecraft.options.gamma();

            // Set the gamma value to 1.0
            gammaOption.set(10.0);

            // Add a breakpoint on the line below
            System.out.println("Gamma changed");
        }
    }

}