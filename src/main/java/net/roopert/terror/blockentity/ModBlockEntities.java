package net.roopert.terror.blockentity;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.roopert.terror.Terror;
import net.roopert.terror.block.ModBlocks;

public class ModBlockEntities  {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Terror.MOD_ID);

//    public static final RegistryObject<BlockEntityType<GraveBlockEntity>> GRAVE_BLOCK =
//            BLOCK_ENTITIES.register("grave_block", () ->
//                    BlockEntityType.Builder.of(GraveBlockEntity::new,
//                            ModBlocks.GRAVE.get()).build(null));









    public static void register(IEventBus eventBus){
        BLOCK_ENTITIES.register(eventBus);
    }

}