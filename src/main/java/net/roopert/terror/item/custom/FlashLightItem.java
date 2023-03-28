package net.roopert.terror.item.custom;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.roopert.terror.GUI.FlashlightGui;
import net.roopert.terror.effect.ModEffects;
import net.roopert.terror.item.ModCreativeModeTab;
import net.roopert.terror.item.Moditems;

import javax.annotation.Nullable;

public class FlashLightItem extends Item {

    public FlashLightItem(Properties tab) {
        super(new Item.Properties().durability(20));
    }

    @Mod.EventBusSubscriber
    public static class FlashLightRightClickedProcedure {

        @SubscribeEvent
        public static void onRightClickItem(PlayerInteractEvent.RightClickItem event) {
            if (event.getHand() != event.getEntity().getUsedItemHand())
                return;
            execute(event, event.getEntity());
        }

        public static void execute(Entity entity) {
            execute(null, entity);
        }

        private static void execute(@Nullable Event event, Entity entity) {
            if (entity == null)
                return;
            if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == Moditems.FLASHLIGHT.get()) {
                if (entity instanceof Player _player) {
                    if (_player.containerMenu == null) {
                        Minecraft.getInstance().setScreen(new FlashlightGui());
                    } else {
                        _player.closeContainer();
                    }
                }
            }
        }
    }
}
