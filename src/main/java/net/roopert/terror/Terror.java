package net.roopert.terror;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.roopert.terror.GUI.ModGuis;
import net.roopert.terror.block.ModBlockStateProperties;
import net.roopert.terror.block.ModBlocks;
import net.roopert.terror.blockentity.ModBlockEntities;
import net.roopert.terror.client.ModClient;
import net.roopert.terror.effect.ModEffects;
import net.roopert.terror.entity.ModEntities;
import net.roopert.terror.item.ModCreativeModeTab;
import net.roopert.terror.item.Moditems;
import net.roopert.terror.sounds.ModSounds;

import net.roopert.terror.world.feature.ModConfiguredFeatures;
import net.roopert.terror.world.feature.ModPlacedFeatures;



// The value here should match an entry in the META-INF/mods.toml file
@Mod(Terror.MOD_ID)
public class Terror
{
    public static final String MOD_ID = "terror";

    public Terror()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        Moditems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModSounds.register(modEventBus);
        ModEffects.register(modEventBus);
        ModGuis.register(modEventBus);
        ModBlockStateProperties.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModEntities.register(modEventBus);


        modEventBus.addListener(ModClient::onRegisterRenderers);
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::addCreative);
        MinecraftForge.EVENT_BUS.register(this);
    }


    private void commonSetup(final FMLCommonSetupEvent event){
        event.enqueueWork(() -> {

        });
    }
    private void addCreative(CreativeModeTabEvent.BuildContents event) {
        if(event.getTab() == ModCreativeModeTab.TERROR) {
            event.accept(Moditems.COPPER_CAMERA);
            event.accept(Moditems.FEAR_SHARD);
            event.accept(Moditems.FLASHLIGHT);
            event.accept(Moditems.HATCHET);
            event.accept(Moditems.GRAVE_DIGGER);
            event.accept(Moditems.GRAVE_DIGGER_TRAY);
            event.accept(Moditems.HATCHET_HEAD);
            event.accept(Moditems.IRON_CAMERA);
            event.accept(Moditems.NETHERITE_CAMERA);
            event.accept(Moditems.PLANT_REMAINS);
            event.accept(Moditems.PLANT_STRING);
            event.accept(Moditems.SINISITE_CAMERA);

            if(event.getTab() == ModCreativeModeTab.TERROR) {
                event.accept(ModBlocks.SIRENIUM);
                event.accept(ModBlocks.GRAVE);
                event.accept(ModBlocks.CURSED_STONE_BLOCK);
                event.accept(ModBlocks.FORGOTTEN_STONE_BLOCK);
                event.accept(ModBlocks.DARK_STONE_BLOCK);



        }
    }



    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

        }
    }
    }
}


