package com.jtprince.coordinateoffset.offsetter.client;

import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientUpdateSign;
import com.jtprince.coordinateoffset.Offset;
import com.jtprince.coordinateoffset.offsetter.packet.PacketReceiveOffsetter;

public class OffsetterClientUpdateSign extends PacketReceiveOffsetter {
    public OffsetterClientUpdateSign() {
        super(PacketType.Play.Client.UPDATE_SIGN);
    }

    @Override
    public void offset(PacketReceiveEvent event, Offset offset) {
        WrapperPlayClientUpdateSign packet = new WrapperPlayClientUpdateSign(event);
        packet.setBlockPosition(unapply(packet.getBlockPosition(), offset));
    }
}
