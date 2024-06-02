package com.jtprince.coordinateoffset.offsetter.client;

import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientVehicleMove;
import com.jtprince.coordinateoffset.Offset;
import com.jtprince.coordinateoffset.offsetter.packet.PacketReceiveOffsetter;

public class OffsetterClientVehicleMove extends PacketReceiveOffsetter {
    public OffsetterClientVehicleMove() {
        super(PacketType.Play.Client.VEHICLE_MOVE);
    }

    @Override
    public void offset(PacketReceiveEvent event, Offset offset) {
        WrapperPlayClientVehicleMove packet = new WrapperPlayClientVehicleMove(event);
        packet.setPosition(unapply(packet.getPosition(), offset));
    }
}
