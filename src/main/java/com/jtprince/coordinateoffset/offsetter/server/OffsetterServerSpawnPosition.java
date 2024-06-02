package com.jtprince.coordinateoffset.offsetter.server;

import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerSpawnPosition;
import com.jtprince.coordinateoffset.Offset;
import com.jtprince.coordinateoffset.offsetter.packet.PacketSendOffsetter;

public class OffsetterServerSpawnPosition extends PacketSendOffsetter {
    public OffsetterServerSpawnPosition() {
        super(PacketType.Play.Server.SPAWN_POSITION);
    }

    @Override
    public void offset(PacketSendEvent event, Offset offset) {
        WrapperPlayServerSpawnPosition packet = new WrapperPlayServerSpawnPosition(event);
        packet.setPosition(apply(packet.getPosition(), offset));
    }
}
