package net.roopert.terror.ModUtil;

import net.minecraft.core.Direction;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class RotateVoxel {

    public static VoxelShape rotate(VoxelShape shape, Direction direction) {
        if (shape.isEmpty()) {
            return shape;
        }

        double xMin = shape.min(Direction.Axis.X);
        double yMin = shape.min(Direction.Axis.Y);
        double zMin = shape.min(Direction.Axis.Z);
        double xMax = shape.max(Direction.Axis.X);
        double yMax = shape.max(Direction.Axis.Y);
        double zMax = shape.max(Direction.Axis.Z);

        switch (direction) {
            case UP:
                return Shapes.box(yMin, 1 - xMax, zMin, yMax, 1 - xMin, zMax);
            case DOWN:
                return Shapes.box(1 - yMax, xMin, zMin, 1 - yMin, xMax, zMax);
            case EAST:
                return Shapes.box(1 - zMax, yMin, xMin, 1 - zMin, yMax, xMax);
            case WEST:
                return Shapes.box(zMin, yMin, 1 - xMax, zMax, yMax, 1 - xMin);
            case NORTH:
                return Shapes.box(xMin, yMin, zMin, xMax, yMax, zMax);
            case SOUTH:
                return Shapes.box(xMin, yMin, 1 - zMax, xMax, yMax, 1 - zMin);
            default:
                throw new IllegalArgumentException("Invalid rotation direction: " + direction);
        }
    }
}
