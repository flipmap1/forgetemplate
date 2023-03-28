package net.roopert.terror.block.custom.grave;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.ToolAction;
import net.roopert.terror.ModUtil.RotateVoxel;
import net.roopert.terror.block.ModBlockStateProperties;
import net.roopert.terror.block.ModBlocks;
import net.roopert.terror.entity.HideableEntity;

import javax.annotation.Nullable;

public class Grave extends HorizontalDirectionalBlock {
    public static final EnumProperty<GravePart> PART = ModBlockStateProperties.GRAVE_PART;
    public static final BooleanProperty OCCUPIED = BlockStateProperties.OCCUPIED;




    public Grave(Properties p_54120_) {
        super(p_54120_);
        this.registerDefaultState(this.stateDefinition.any().setValue(PART, GravePart.FOOT).setValue(FACING, Direction.NORTH).setValue(OCCUPIED, false));

    }




    public RenderShape getRenderShape(BlockState p_49545_) {
        return RenderShape.MODEL;
    }





    // VOXELS
    private static VoxelShape makeFootShape() {
        VoxelShape Footshape = Shapes.empty();
        Footshape = Shapes.join(Footshape, Shapes.box(0.0625, 0, 0.0625, 0.9375, 0.09375, 1), BooleanOp.OR);
        return Footshape;
    }
    private static VoxelShape makeHeadShape() {
            VoxelShape Headshape = Shapes.empty();
            Headshape = Shapes.join(Headshape, Shapes.box(0.0625, 0, 0, 0.9375, 0.09375, 0.9375), BooleanOp.OR);
            return Headshape;
        }

    VoxelShape NorthHeadShape = makeHeadShape(); // create the voxel shape
    VoxelShape SouthHeadShape = RotateVoxel.rotate(NorthHeadShape, Direction.SOUTH); // rotate the voxel shape
    VoxelShape EastHeadShape = RotateVoxel.rotate(NorthHeadShape, Direction.EAST); // rotate the voxel shape
    VoxelShape WestHeadShape = RotateVoxel.rotate(NorthHeadShape, Direction.WEST); // rotate the voxel shape
    VoxelShape NorthFootShape = makeFootShape(); // create the voxel shape
    VoxelShape SouthFootShape = RotateVoxel.rotate(NorthFootShape, Direction.SOUTH); // rotate the voxel shape
    VoxelShape EastFootShape = RotateVoxel.rotate(NorthFootShape, Direction.EAST); // rotate the voxel shape
    VoxelShape WestFootShape = RotateVoxel.rotate(NorthFootShape, Direction.WEST); // rotate the voxel shape

    public VoxelShape getShape(BlockState p_49547_, BlockGetter p_49548_, BlockPos p_49549_, CollisionContext p_49550_) {
        Direction direction = p_49547_.getValue(FACING);
        GravePart part = p_49547_.getValue(PART);

        if (part == GravePart.FOOT) {
            return switch (direction) {
                case NORTH -> SouthFootShape;
                case SOUTH -> NorthFootShape;
                case WEST -> EastFootShape;
                default -> WestFootShape;
            };
        } else {
            return switch (direction) {
                case NORTH -> SouthHeadShape;
                case SOUTH -> NorthHeadShape;
                case WEST -> EastHeadShape;
                default -> WestHeadShape;
            };
        }
    }


    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_49532_) {
        p_49532_.add(FACING, PART, OCCUPIED);
    }





    private static Direction getNeighbourDirection(GravePart p_49534_, Direction p_49535_) {
        return p_49534_ == GravePart.FOOT ? p_49535_ : p_49535_.getOpposite();
    }


    public BlockState updateShape(BlockState p_49525_, Direction p_49526_, BlockState p_49527_, LevelAccessor p_49528_, BlockPos p_49529_, BlockPos p_49530_) {
        if (p_49526_ == getNeighbourDirection(p_49525_.getValue(PART), p_49525_.getValue(FACING))) {
            return p_49527_.is(this) && p_49527_.getValue(PART) != p_49525_.getValue(PART) ? p_49525_.setValue(OCCUPIED, p_49527_.getValue(OCCUPIED)) : Blocks.AIR.defaultBlockState();
        } else {
            return super.updateShape(p_49525_, p_49526_, p_49527_, p_49528_, p_49529_, p_49530_);
        }
    }


    public static boolean isValidGround(BlockState blockState) {
        Block[] validBlocks = {Blocks.DIRT, Blocks.GRASS_BLOCK, Blocks.PODZOL, Blocks.COARSE_DIRT};
        for (Block block : validBlocks) {
            if (blockState.is(block)) {
                return true;
            }
        }
        return false;
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext p_49479_) {
        Direction direction = p_49479_.getHorizontalDirection();
        BlockPos blockpos = p_49479_.getClickedPos();
        BlockPos blockpos1 = blockpos.relative(direction);
        Level level = p_49479_.getLevel();
        if (!isValidGround(level.getBlockState(blockpos.below())) || !isValidGround(level.getBlockState(blockpos1.below()))) {
            return null;
        }
        return level.getBlockState(blockpos1).canBeReplaced(p_49479_) && level.getWorldBorder().isWithinBounds(blockpos1) ? this.defaultBlockState().setValue(FACING, direction) : null;
    }


    public void setPlacedBy(Level p_49499_, BlockPos p_49500_, BlockState p_49501_, @Nullable LivingEntity p_49502_, ItemStack p_49503_) {
        super.setPlacedBy(p_49499_, p_49500_, p_49501_, p_49502_, p_49503_);
        if (!p_49499_.isClientSide) {
            BlockPos blockpos = p_49500_.relative(p_49501_.getValue(FACING));
            p_49499_.setBlock(blockpos, p_49501_.setValue(PART, GravePart.HEAD), 3);
            p_49499_.blockUpdated(p_49500_, Blocks.AIR);
            p_49501_.updateNeighbourShapes(p_49499_, p_49500_, 3);
        }
    }








    public long getSeed(BlockState p_49522_, BlockPos p_49523_) {
        BlockPos blockpos = p_49523_.relative(p_49522_.getValue(FACING), p_49522_.getValue(PART) == GravePart.HEAD ? 0 : 1);
        return Mth.getSeed(blockpos.getX(), p_49523_.getY(), blockpos.getZ());
    }
    public void playerWillDestroy(Level p_49505_, BlockPos p_49506_, BlockState p_49507_, Player p_49508_) {
        if (!p_49505_.isClientSide && p_49508_.isCreative()) {
            GravePart gravepart = p_49507_.getValue(PART);
            if (gravepart == GravePart.FOOT) {
                BlockPos blockpos = p_49506_.relative(getNeighbourDirection(gravepart, p_49507_.getValue(FACING)));
                BlockState blockstate = p_49505_.getBlockState(blockpos);
                if (blockstate.is(this) && blockstate.getValue(PART) == GravePart.HEAD) {
                    p_49505_.setBlock(blockpos, Blocks.AIR.defaultBlockState(), 35);
                    p_49505_.levelEvent(p_49508_, 2001, blockpos, Block.getId(blockstate));
                }
            }
        }
        super.playerWillDestroy(p_49505_, p_49506_, p_49507_, p_49508_);
    }








    public PushReaction getPistonPushReaction(BlockState p_49556_) {
        return PushReaction.DESTROY;
    }

    public boolean isPathfindable(BlockState p_49510_, BlockGetter p_49511_, BlockPos p_49512_, PathComputationType p_49513_) {
        return false;
    }



    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result)
    {
        return HideableEntity.create(level, pos, 0.4, player, state.getValue(FACING));
    }
}