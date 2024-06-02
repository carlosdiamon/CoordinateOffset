package com.jtprince.coordinateoffset.offsetter.server;

import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.protocol.teleport.RelativeFlag;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerPlayerPositionAndLook;
import com.jtprince.coordinateoffset.Offset;
import com.jtprince.coordinateoffset.offsetter.packet.PacketSendOffsetter;

public class OffsetterServerPlayerPositionAndLook extends PacketSendOffsetter {
    public OffsetterServerPlayerPositionAndLook() {
        super(PacketType.Play.Server.PLAYER_POSITION_AND_LOOK);
    }
    @Override
    public void offset(PacketSendEvent event, Offset offset) {
        WrapperPlayServerPlayerPositionAndLook packet = new WrapperPlayServerPlayerPositionAndLook(event);
        if (!packet.isRelativeFlag(RelativeFlag.X)) {
            packet.setX(applyX(packet.getX(), offset));
        }
        if (!packet.isRelativeFlag(RelativeFlag.Z)) {
            packet.setZ(applyZ(packet.getZ(), offset));
        }
    }
}
