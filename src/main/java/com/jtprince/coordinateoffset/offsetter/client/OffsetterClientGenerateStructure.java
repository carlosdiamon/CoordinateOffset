package com.jtprince.coordinateoffset.offsetter.client;

import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientGenerateStructure;
import com.jtprince.coordinateoffset.Offset;
import com.jtprince.coordinateoffset.offsetter.packet.PacketReceiveOffsetter;

public class OffsetterClientGenerateStructure extends PacketReceiveOffsetter {
    public OffsetterClientGenerateStructure() {
        super(PacketType.Play.Client.GENERATE_STRUCTURE);
    }
    @Override
    public void offset(PacketReceiveEvent event, Offset offset) {
        WrapperPlayClientGenerateStructure packet = new WrapperPlayClientGenerateStructure(event);
        packet.setBlockPosition(unapply(packet.getBlockPosition(), offset));
    }
}
