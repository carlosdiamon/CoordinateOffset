package com.jtprince.coordinateoffset.offsetter.server;

import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerExplosion;
import com.jtprince.coordinateoffset.Offset;
import com.jtprince.coordinateoffset.offsetter.packet.PacketSendOffsetter;

public class OffsetterServerExplosion extends PacketSendOffsetter {
    public OffsetterServerExplosion() {
        super(PacketType.Play.Server.EXPLOSION);
    }

    @Override
    public void offset(PacketSendEvent event, Offset offset) {
        WrapperPlayServerExplosion packet = new WrapperPlayServerExplosion(event);
        packet.setPosition(apply(packet.getPosition(), offset));
        packet.setRecords(packet.getRecords().stream().map(v -> apply(v, offset)).toList());
    }
}
