package com.jtprince.coordinateoffset.offsetter.packet;

import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.component.ComponentTypes;
import com.github.retrooper.packetevents.protocol.component.builtin.item.LodestoneTracker;
import com.github.retrooper.packetevents.protocol.item.ItemStack;
import com.github.retrooper.packetevents.protocol.item.type.ItemTypes;
import com.github.retrooper.packetevents.protocol.nbt.NBTCompound;
import com.github.retrooper.packetevents.protocol.nbt.NBTInt;
import com.github.retrooper.packetevents.protocol.packettype.PacketTypeCommon;
import com.github.retrooper.packetevents.protocol.player.User;
import com.github.retrooper.packetevents.protocol.world.Location;
import com.github.retrooper.packetevents.protocol.world.WorldBlockPosition;
import com.github.retrooper.packetevents.protocol.world.chunk.Column;
import com.github.retrooper.packetevents.util.Vector3d;
import com.github.retrooper.packetevents.util.Vector3i;
import com.jtprince.coordinateoffset.CoordinateOffset;
import com.jtprince.coordinateoffset.Offset;

import java.util.Optional;

public abstract class PacketReceiveOffsetter extends AbstractPacketOffsetter<PacketReceiveEvent> {

    public PacketReceiveOffsetter(PacketTypeCommon... packetTypes) {
        super(packetTypes);
    }

    public Vector3d unapply(Vector3d vec, Offset offset) {
        return new Vector3d(vec.x + offset.x(), vec.y, vec.z + offset.z());
    }

    public Vector3i unapply(Vector3i vec, Offset offset) {
        return new Vector3i(vec.x + offset.x(), vec.y, vec.z + offset.z());
    }

    public Location unapply(Location loc, Offset offset) {
        loc.setPosition(new Vector3d(loc.getX() + offset.x(), loc.getY(), loc.getZ() + offset.z()));
        return loc;
    }

}
