package net.roopert.terror.event;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.roopert.terror.ModUtil.KeyBindings;
import net.roopert.terror.Terror;
import net.roopert.terror.entity.HideableEntity;
import net.minecraftforge.event.entity.ProjectileImpactEvent;

import java.util.HashMap;
import java.util.Map;

public class ClientEvents {
    @Mod.EventBusSubscriber(modid = Terror.MOD_ID, value = Dist.CLIENT)
    public static class ClientForgeEvents {


        //PEAKING FUNCTIONALITY WHILE HIDING
        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event) {
            if (KeyBindings.PEAK_KEY.consumeClick()) {
                Minecraft mc = Minecraft.getInstance();
                if (mc.player != null && mc.player.getVehicle() instanceof HideableEntity) {
                    HideableEntity vehicle = (HideableEntity) mc.player.getVehicle();
                    double currentOffset = vehicle.getPassengersRidingOffset();
                    double newOffset = currentOffset == -2.0 ? -1.0 : -2.0;
                    vehicle.setPassengersRidingOffset(newOffset);
                }
            }
        }
        private static final Map<Mob, Double> originalVisibilityMap = new HashMap<>();

        @SubscribeEvent
        public static void onLivingVisibility(LivingEvent.LivingVisibilityEvent event) {
            if (event.getEntity() instanceof Mob) {
                Mob mob = (Mob) event.getEntity();
                if (mob.getTarget() != null && mob.getTarget().isPassenger()) {
                    Entity vehicle = mob.getTarget().getVehicle();
                    if (vehicle instanceof HideableEntity) {
                        HideableEntity hideableEntity = (HideableEntity) vehicle;
                        if (hideableEntity.getPassengersRidingOffset() == -2.0) {
                            if (!originalVisibilityMap.containsKey(mob)) {
                                originalVisibilityMap.put(mob, event.getVisibilityModifier());
                            }
                            event.modifyVisibility(0); // Make the player undetectable
                        } else {
                            Double originalVisibility = originalVisibilityMap.get(mob);
                            if (originalVisibility != null) {
                                event.modifyVisibility(originalVisibility / event.getVisibilityModifier());
                            }
                        }
                    }
                }
            }
        }


        @SubscribeEvent
        public void onProjectileImpact(ProjectileImpactEvent event) {
            if (event.getRayTraceResult() instanceof EntityHitResult) {
                EntityHitResult entityHitResult = (EntityHitResult) event.getRayTraceResult();
                Entity hitEntity = entityHitResult.getEntity();

                if (hitEntity instanceof Player) {
                    Player player = (Player) hitEntity;
                    if (player.getVehicle() instanceof HideableEntity) {
                        HideableEntity hideableEntity = (HideableEntity) player.getVehicle();
                        if (hideableEntity.getPassengersRidingOffset() == -2.0) {
                            event.setCanceled(true); // Cancel the arrow impact event
                        }
                    }
                }
            }
        }







        @Mod.EventBusSubscriber(modid = Terror.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
        public static class ClientModBusEvents {
            @SubscribeEvent
            public static void onKeyRegister(RegisterKeyMappingsEvent event) {
                event.register(KeyBindings.PEAK_KEY);

            }
        }
    }
}