package net.roopert.terror.world.feature.stateproviders;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProviderType;

public class RandomHorizontalDirectionProvider extends BlockStateProvider {
    public static final Codec<RandomHorizontalDirectionProvider> CODEC;
    private final BlockState state;

    static {
        CODEC = BlockState.CODEC.fieldOf("state").xmap(RandomHorizontalDirectionProvider::new, provider -> provider.state).codec();
    }

    public RandomHorizontalDirectionProvider(BlockState state) {
        this.state = state;
    }

    @Override
    protected BlockStateProviderType<?> type() {
        return BlockStateProviderType.SIMPLE_STATE_PROVIDER; // You can create a custom provider type if necessary.
    }

    @Override
    public BlockState getState(RandomSource random, BlockPos pos) {
        Direction randomDirection = Direction.Plane.HORIZONTAL.getRandomDirection(random);
        return state.setValue(HorizontalDirectionalBlock.FACING, randomDirection);
    }
}
