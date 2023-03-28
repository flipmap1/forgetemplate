package net.roopert.terror.block;


import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.roopert.terror.Terror;
import net.roopert.terror.block.custom.Sirenium;
import net.roopert.terror.block.custom.grave.Grave;
import net.roopert.terror.item.ModCreativeModeTab;
import java.util.function.Supplier;
import static net.roopert.terror.item.Moditems.ITEMS;


public class ModBlocks extends Block {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Terror.MOD_ID);


//stone
    public static final RegistryObject<Block> CURSED_STONE_BLOCK = registerBlock("cursed_stone_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(2f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> FORGOTTEN_STONE_BLOCK = registerBlock("forgotten_stone_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(2f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> DARK_STONE_BLOCK = registerBlock("dark_stone_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(2f).requiresCorrectToolForDrops()));

//ores
    public static final RegistryObject<Block> SILVER_ORE = registerBlock("silver_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(6f).requiresCorrectToolForDrops()));



    //Vegetation
    public static final RegistryObject<Block> SIRENIUM = registerBlock("sirenium",
            Sirenium::new);


    public static final RegistryObject<Block> GRAVE = registerBlock("grave",
            () -> new Grave(Properties.of(Material.DIRT)
            .strength(6f).requiresCorrectToolForDrops()));




    public ModBlocks(Properties p_49795_) {
        super(p_49795_);
    }


    //Code
    public static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);

        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> Block) {
        ITEMS.register(name, () -> new BlockItem(Block.get(), new Item.Properties()));
    }
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
