package com.jtprince.coordinateoffset.offsetter.client;

import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientUpdateCommandBlock;
import com.jtprince.coordinateoffset.Offset;
import com.jtprince.coordinateoffset.offsetter.packet.PacketReceiveOffsetter;

public class OffsetterClientUpdateCommandBlock extends PacketReceiveOffsetter {
    public OffsetterClientUpdateCommandBlock() {
        super(PacketType.Play.Client.UPDATE_COMMAND_BLOCK);
    }

    @Override
    public void offset(PacketReceiveEvent event, Offset offset) {
        WrapperPlayClientUpdateCommandBlock packet = new WrapperPlayClientUpdateCommandBlock(event);
        packet.setPosition(unapply(packet.getPosition(), offset));
    }
}
