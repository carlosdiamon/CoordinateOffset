package com.jtprince.coordinateoffset.offsetter.server;

import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerSpawnPainting;
import com.jtprince.coordinateoffset.Offset;
import com.jtprince.coordinateoffset.offsetter.packet.PacketSendOffsetter;

public class OffsetterServerSpawnPainting extends PacketSendOffsetter {
    public OffsetterServerSpawnPainting() {
        // Removed in 1.19
        super(PacketType.Play.Server.SPAWN_PAINTING);
    }

    @Override
    public void offset(PacketSendEvent event, Offset offset) {
        WrapperPlayServerSpawnPainting packet = new WrapperPlayServerSpawnPainting(event);
        packet.setPosition(apply(packet.getPosition(), offset));
    }
}
