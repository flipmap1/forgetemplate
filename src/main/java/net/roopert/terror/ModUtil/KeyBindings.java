package net.roopert.terror.ModUtil;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;

import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class KeyBindings{
    public static final String KEY_CATEGORY_TERROR = "key.category.terror.terror";
    public static final String KEY_PEAKWHILEHIDING = "key.terror.peakwhilehiding";

    public static final KeyMapping PEAK_KEY = new KeyMapping(KEY_PEAKWHILEHIDING, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_V, KEY_CATEGORY_TERROR);
}