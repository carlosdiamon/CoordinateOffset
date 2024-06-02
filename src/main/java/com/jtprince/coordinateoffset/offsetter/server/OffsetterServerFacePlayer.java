package com.jtprince.coordinateoffset.offsetter.server;

import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerFacePlayer;
import com.jtprince.coordinateoffset.Offset;
import com.jtprince.coordinateoffset.offsetter.packet.PacketSendOffsetter;

public class OffsetterServerFacePlayer extends PacketSendOffsetter {
    public OffsetterServerFacePlayer() {
        super(PacketType.Play.Server.FACE_PLAYER);
    }

    @Override
    public void offset(PacketSendEvent event, Offset offset) {
        WrapperPlayServerFacePlayer packet = new WrapperPlayServerFacePlayer(event);
        packet.setTargetPosition(apply(packet.getTargetPosition(), offset));
    }
}
