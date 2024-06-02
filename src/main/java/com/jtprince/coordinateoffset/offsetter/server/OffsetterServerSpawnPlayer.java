package com.jtprince.coordinateoffset.offsetter.server;

import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerSpawnPlayer;
import com.jtprince.coordinateoffset.Offset;
import com.jtprince.coordinateoffset.offsetter.packet.PacketSendOffsetter;

public class OffsetterServerSpawnPlayer extends PacketSendOffsetter {
    public OffsetterServerSpawnPlayer() {
        // Removed in 1.19.3
        super(PacketType.Play.Server.SPAWN_PLAYER);
    }

    @Override
    public void offset(PacketSendEvent event, Offset offset) {
        WrapperPlayServerSpawnPlayer packet = new WrapperPlayServerSpawnPlayer(event);
        packet.setPosition(apply(packet.getPosition(), offset));
    }
}
