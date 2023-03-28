package net.roopert.terror.GUI;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;

public class ModGuis {
    public static final ResourceLocation FLASHLIGHT_GUI = new ResourceLocation("terror", "flashlight_gui");


    public static void register(IEventBus eventBus) {
        eventBus.register(FlashlightGui.class);
    }
}

