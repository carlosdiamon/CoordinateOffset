package com.jtprince.coordinateoffset.offsetter.packet;

import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketTypeCommon;
import com.github.retrooper.packetevents.protocol.player.User;
import com.github.retrooper.packetevents.protocol.world.chunk.Column;
import com.github.retrooper.packetevents.util.Vector3d;
import com.github.retrooper.packetevents.util.Vector3i;
import com.jtprince.coordinateoffset.Offset;
import com.jtprince.coordinateoffset.offsetter.OffsettedColumn;

public abstract class PacketSendOffsetter extends AbstractPacketOffsetter<PacketSendEvent> {

    public PacketSendOffsetter(PacketTypeCommon... packetTypes) {
        super(packetTypes);
    }

    public Vector3d apply(Vector3d vec, Offset offset) {
        return new Vector3d(vec.x - offset.x(), vec.y, vec.z - offset.z());
    }

    public Vector3i apply(Vector3i vec, Offset offset) {
        return new Vector3i(vec.x - offset.x(), vec.y, vec.z - offset.z());
    }

    public Vector3i applyChunk(Vector3i vec, Offset offset) {
        return new Vector3i(vec.x - offset.chunkX(), vec.y, vec.z - offset.chunkZ());
    }

    public double applyX(double x, Offset offset) {
        return x - offset.x();
    }

    public double applyZ(double z, Offset offset) {
        return z - offset.z();
    }

    public int applyChunkX(int chunkX, Offset offset) {
        return chunkX - offset.chunkX();
    }

    public int applyChunkZ(int chunkZ, Offset offset) {
        return chunkZ - offset.chunkZ();
    }

    public OffsettedColumn applyColumn(Column column, Offset offset, User user) {
        if (column instanceof OffsettedColumn) return (OffsettedColumn) column;
        return new OffsettedColumn(column, offset, user);
    }

    public Vector3i applyTimes8(Vector3i vec, Offset offset) {
        // Used for sound effects
        return new Vector3i(vec.x - (offset.x() * 8), vec.y, vec.z - (offset.z() * 8));
    }

}