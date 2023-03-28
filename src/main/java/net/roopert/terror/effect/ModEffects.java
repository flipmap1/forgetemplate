package net.roopert.terror.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.roopert.terror.Terror;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS
            = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Terror.MOD_ID);

    public static final RegistryObject<MobEffect> FLASHLIGHT_EFFECT
            = MOB_EFFECTS.register("flashlight_effect", FlashlightEffect::new);


    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
