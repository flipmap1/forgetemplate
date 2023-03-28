package net.roopert.terror.client;

import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.roopert.terror.client.renderer.HideableEntityRenderer;
import net.roopert.terror.entity.ModEntities;

public class ModClient {
    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.HIDEABLEENTITY.get(), HideableEntityRenderer::new);
    }
}
