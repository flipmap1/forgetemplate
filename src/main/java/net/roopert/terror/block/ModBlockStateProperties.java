package net.roopert.terror.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.roopert.terror.Terror;
import net.roopert.terror.block.custom.grave.GravePart;

public class ModBlockStateProperties {
    public static final EnumProperty<GravePart> GRAVE_PART = EnumProperty.create("grave_part", GravePart.class);

    public static void register(IEventBus eventBus) {
        DeferredRegister<Block> deferredRegister = DeferredRegister.create(ForgeRegistries.BLOCKS, Terror.MOD_ID);
        deferredRegister.register(eventBus);
    }
}
