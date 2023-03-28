package net.roopert.terror.entity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.roopert.terror.Terror;



public class ModEntities
{
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Terror.MOD_ID);

    public static final RegistryObject<EntityType<HideableEntity>> HIDEABLEENTITY =
            ENTITY_TYPES.register("hideable_entity",
                    () -> EntityType.Builder.of(HideableEntity::new, MobCategory.MISC)
                            .sized(0.0F, 0.0F)
                            .build(new ResourceLocation(Terror.MOD_ID, "hideable_entity").toString()));


    public static void register(IEventBus eventBus){
        ENTITY_TYPES.register(eventBus);
    }
}