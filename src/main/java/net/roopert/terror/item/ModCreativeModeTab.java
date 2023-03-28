package net.roopert.terror.item;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.roopert.terror.Terror;
@Mod.EventBusSubscriber(modid = Terror.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModCreativeModeTab {
    public static CreativeModeTab TERROR;
    @SubscribeEvent
    public static void registerCreativeModeTabs(CreativeModeTabEvent.Register event) {
        TERROR = event.registerCreativeModeTab(new ResourceLocation(Terror.MOD_ID, "terror"),
            builder -> builder.icon(() -> new ItemStack(Moditems.COPPER_CAMERA.get())).title(Component.literal("Terror")).build());

    }
}
