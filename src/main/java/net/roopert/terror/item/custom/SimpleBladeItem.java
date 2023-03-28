package net.roopert.terror.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.items.ItemHandlerHelper;
import net.roopert.terror.block.ModBlocks;
import net.roopert.terror.block.custom.Sirenium;
import net.roopert.terror.item.ModCreativeModeTab;
import net.roopert.terror.item.Moditems;

import javax.annotation.Nullable;


public class SimpleBladeItem extends Item {
    public SimpleBladeItem(Properties tab) {
        super(new Item.Properties().durability(20));
    }


    @Mod.EventBusSubscriber
    public static class SimpleBladeRightClickedProcedure {
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
            if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == Moditems.SIMPLE_BLADE.get()
                    && (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem() == ModBlocks.SIRENIUM.get().asItem()) {

                if (entity instanceof Player _player) {
                    ItemStack _setstack = new ItemStack(Moditems.PLANT_STRING.get());
                    _setstack.setCount(1);
                    ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
                }
                if (entity instanceof Player _player) {
                    ItemStack _stktoremove = new ItemStack(ModBlocks.SIRENIUM.get());
                    _player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1,
                            _player.inventoryMenu.getCraftSlots());
                }
            }
        }
    }
}

