package net.roopert.terror.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.roopert.terror.entity.HideableEntity;

public class HideableEntityRenderer extends EntityRenderer<HideableEntity>
{
    public HideableEntityRenderer(EntityRendererProvider.Context context)
    {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(HideableEntity hideableEntity)
    {
        return null;
    }

    @Override
    protected void renderNameTag(HideableEntity entity, Component component, PoseStack stack, MultiBufferSource source, int light) {}
}