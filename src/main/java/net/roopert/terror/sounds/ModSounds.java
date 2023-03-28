package net.roopert.terror.sounds;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.roopert.terror.Terror;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Terror.MOD_ID);

    // Sounds
    public static final RegistryObject<SoundEvent> SIRENIUM_SCREAM1 =
            registerSoundEvent("sirenium_scream1", 1.0f, 1.0f, false, 16.0f);
    public static final RegistryObject<SoundEvent> FLASHLIGHT =
            registerSoundEvent("flashlight", 0.5f, 1.0f, false, 5.0f);
    public static final RegistryObject<SoundEvent> SIRENIUM_SCREAM3 =
            registerSoundEvent("sirenium_scream3", 1.0f, 1.0f, false, 16.0f);
    public static final RegistryObject<SoundEvent> TEST =
            registerSoundEvent("test", 1.0f, 1.0f, false, 16.0f);

    private static RegistryObject<SoundEvent> registerSoundEvent(String name, float volume, float pitch, boolean looping, float range) {
        return SOUND_EVENTS.register(name, () ->
                SoundEvent.createFixedRangeEvent(new ResourceLocation(Terror.MOD_ID, name), volume));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
