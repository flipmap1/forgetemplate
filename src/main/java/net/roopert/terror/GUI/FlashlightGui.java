package net.roopert.terror.GUI;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class FlashlightGui extends Screen {

    private static final int CIRCLE_SIZE = 64; // Change as needed
    private static final int CIRCLE_X = (int) (0.5 * (Minecraft.getInstance().getWindow().getGuiScaledWidth() - CIRCLE_SIZE));
    private static final int CIRCLE_Y = (int) (0.5 * (Minecraft.getInstance().getWindow().getGuiScaledHeight() - CIRCLE_SIZE));
    private static final int CENTER_COLOR = 0xFFFFF9;
    private static final int EDGE_COLOR = 0x0F0F0F;

    public FlashlightGui() {
        super(null);

    }

    @Override
    public void render(PoseStack matrices, int mouseX, int mouseY, float delta) {
        Minecraft minecraft = Minecraft.getInstance();
        RenderSystem.enableBlend();

        int centerX = CIRCLE_X + CIRCLE_SIZE / 2;
        int centerY = CIRCLE_Y + CIRCLE_SIZE / 2;
        int maxDistance = (int) Math.sqrt(Math.pow(CIRCLE_SIZE / 2, 2) + Math.pow(CIRCLE_SIZE / 2, 2));

        for (int x = 0; x < CIRCLE_SIZE; x++) {
            for (int y = 0; y < CIRCLE_SIZE; y++) {
                int distance = (int) Math.sqrt(Math.pow(x - CIRCLE_SIZE / 2, 2) + Math.pow(y - CIRCLE_SIZE / 2, 2));
                if (distance > CIRCLE_SIZE / 2) {
                    continue;
                }
                int alpha = 255;
                if (distance < maxDistance) {
                    alpha = (int) (255 * (1 - (double) distance / maxDistance));
                }
                int color = (int) Mth.lerp(alpha / 255.0f, EDGE_COLOR, CENTER_COLOR);
                blit(matrices, CIRCLE_X + x, CIRCLE_Y + y, 0, 0, 1, 1, color, 0);
            }
        }

        RenderSystem.disableBlend();
    }

}
