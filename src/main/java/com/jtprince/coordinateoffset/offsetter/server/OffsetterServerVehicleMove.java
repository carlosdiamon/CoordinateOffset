package com.jtprince.coordinateoffset.offsetter.server;

import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerVehicleMove;
import com.jtprince.coordinateoffset.Offset;
import com.jtprince.coordinateoffset.offsetter.packet.PacketSendOffsetter;

public class OffsetterServerVehicleMove extends PacketSendOffsetter {
    public OffsetterServerVehicleMove() {
        super(PacketType.Play.Server.VEHICLE_MOVE);
    }

    @Override
    public void offset(PacketSendEvent event, Offset offset) {
        WrapperPlayServerVehicleMove packet = new WrapperPlayServerVehicleMove(event);
        packet.setPosition(apply(packet.getPosition(), offset));
    }
}
