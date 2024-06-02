package com.jtprince.coordinateoffset.offsetter.packet;

import com.github.retrooper.packetevents.event.ProtocolPacketEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketTypeCommon;
import com.jtprince.coordinateoffset.Offset;

public abstract class AbstractPacketOffsetter<T extends ProtocolPacketEvent<Object>> {
    private final PacketTypeCommon[] packetTypes;

    public AbstractPacketOffsetter(PacketTypeCommon... packetTypes) {
        this.packetTypes = packetTypes;
    }

    public abstract void offset(T event, Offset offset);

    public PacketTypeCommon[] getPacketTypes() {
        return packetTypes;
    }
}