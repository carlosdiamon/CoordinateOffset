package com.jtprince.util;

import com.github.retrooper.packetevents.protocol.component.ComponentTypes;
import com.github.retrooper.packetevents.protocol.component.builtin.item.LodestoneTracker;
import com.github.retrooper.packetevents.protocol.item.ItemStack;
import com.github.retrooper.packetevents.protocol.item.type.ItemTypes;
import com.github.retrooper.packetevents.protocol.nbt.NBTCompound;
import com.github.retrooper.packetevents.protocol.nbt.NBTInt;
import com.github.retrooper.packetevents.protocol.world.WorldBlockPosition;
import com.jtprince.coordinateoffset.CoordinateOffset;
import com.jtprince.coordinateoffset.Offset;

import java.util.Optional;

public final class PacketUtil {

    public static ItemStack unapplyItemStack(ItemStack item, Offset offset) {
        return applyItemStack(item, offset.negate());
    }

    public static ItemStack applyItemStack(ItemStack item, Offset offset) {
        if (item == null) {
            return null;
        }

        if (item.getType() != ItemTypes.COMPASS) {
            return item;
        }

        // Up to 1.20.4 only: NBT tags
        NBTCompound nbt = item.getNBT();
        if (nbt != null) {
            NBTCompound lodestonePos = nbt.getCompoundTagOrNull("LodestonePos");
            if (lodestonePos != null) {
                lodestonePos.setTag("X", new NBTInt(lodestonePos.getNumberTagOrThrow("X").getAsInt() - offset.x()));
                lodestonePos.setTag("Z", new NBTInt(lodestonePos.getNumberTagOrThrow("Z").getAsInt() - offset.z()));
            }
        }

        // 1.20.5+ only: Components
        Optional<?> lodestoneComponent = null;
        try {
            lodestoneComponent = item.getComponents().getPatches().get(ComponentTypes.LODESTONE_TRACKER);
        } catch (NoSuchMethodError e) {
            // No error logged here because this Components branch only affects 1.20.5+, and PE will hit other
            //  issues if an outdated version is installed. (i.e. this branch only happens on <1.20.5 where it does
            //  not matter)
            CoordinateOffset.getInstance().getLogger().fine("Outdated PacketEvents! Failed to get item components.");
        }

        if (lodestoneComponent != null
                && lodestoneComponent.isPresent()
                && lodestoneComponent.get() instanceof LodestoneTracker lodestone
                && lodestone.getTarget() != null) {
            lodestone.setTarget(applyWorldBlock(lodestone.getTarget(), offset));
        }

        return item;
    }

    public static WorldBlockPosition applyWorldBlock(WorldBlockPosition pos, Offset offset) {
        // TODO: When available, this could respect the offset of the specific world instead of the Player's current one
        return new WorldBlockPosition(pos.getWorld(),
                pos.getBlockPosition().x - offset.x(), pos.getBlockPosition().y, pos.getBlockPosition().z - offset.z());
    }

}
