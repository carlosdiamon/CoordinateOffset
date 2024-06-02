package com.jtprince.coordinateoffset.offsetter.server;

import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerSpawnEntity;
import com.jtprince.coordinateoffset.Offset;
import com.jtprince.coordinateoffset.offsetter.packet.PacketSendOffsetter;

public class OffsetterServerSpawnEntity extends PacketSendOffsetter {
    public OffsetterServerSpawnEntity() {
        super(PacketType.Play.Server.SPAWN_ENTITY);
    }

    @Override
    public void offset(PacketSendEvent event, Offset offset) {
        WrapperPlayServerSpawnEntity packet = new WrapperPlayServerSpawnEntity(event);
        packet.setPosition(apply(packet.getPosition(), offset));
    }
}
