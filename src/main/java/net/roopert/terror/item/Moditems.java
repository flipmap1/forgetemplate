package net.roopert.terror.item;


import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.roopert.terror.Terror;
import net.roopert.terror.item.custom.FlashLightItem;
import net.roopert.terror.item.custom.SimpleBladeItem;

public class Moditems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Terror.MOD_ID);
//TOOLS
    public static final RegistryObject<Item> SIMPLE_BLADE = ITEMS.register("simple_blade",
           () -> new SimpleBladeItem(new Item.Properties()));
    public static final RegistryObject<Item> FLASHLIGHT = ITEMS.register("flashlight",
            () -> new FlashLightItem(new Item.Properties()));

    public static final RegistryObject<Item> GRAVE_DIGGER = ITEMS.register("grave_digger",
    () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> GRAVE_DIGGER_TRAY = ITEMS.register("grave_digger_tray",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> HATCHET = ITEMS.register("hatchet",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> HATCHET_HEAD = ITEMS.register("hatchet_head",
            () -> new Item(new Item.Properties()));


//EARLY GAME
    public static final RegistryObject<Item> PLANT_REMAINS = ITEMS.register("plant_remains",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> FEAR_SHARD = ITEMS.register("fear_shard",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PLANT_STRING = ITEMS.register("plant_string",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ROTTEN_STRAND = ITEMS.register("rotten_strand",
            () -> new Item(new Item.Properties()));


//ORES
    public static final RegistryObject<Item> RAW_SILVER = ITEMS.register("raw_silver",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SILVER_INGOT = ITEMS.register("silver_ingot",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SILVER_NUGGET = ITEMS.register("silver_nugget",
            () -> new Item(new Item.Properties()));

//CAMERA
    public static final RegistryObject<Item> COPPER_CAMERA = ITEMS.register("copper_camera",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> IRON_CAMERA = ITEMS.register("iron_camera",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> NETHERITE_CAMERA = ITEMS.register("netherite_camera",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SINISITE_CAMERA = ITEMS.register("sinisite_camera",
            () -> new Item(new Item.Properties()));



    public static void  register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
