package net.roopert.terror.block.custom.grave;

import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

public enum GravePart implements StringRepresentable {
    HEAD("head"),
    FOOT("foot");

    private final String name;

    GravePart(String p_61339_) {
        this.name = p_61339_;
    }

    public String toString() {
        return this.name;
    }

    public @NotNull String getSerializedName() {
        return this.name;
    }
}