package com.jtprince.coordinateoffset.offsetter.server;

import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerEntityTeleport;
import com.jtprince.coordinateoffset.Offset;
import com.jtprince.coordinateoffset.offsetter.packet.PacketSendOffsetter;

public class OffsetterServerEntityTeleport extends PacketSendOffsetter {
    public OffsetterServerEntityTeleport() {
        super(PacketType.Play.Server.ENTITY_TELEPORT);
    }

    @Override
    public void offset(PacketSendEvent event, Offset offset) {
        WrapperPlayServerEntityTeleport packet = new WrapperPlayServerEntityTeleport(event);
        packet.setPosition(apply(packet.getPosition(), offset));
    }
}
