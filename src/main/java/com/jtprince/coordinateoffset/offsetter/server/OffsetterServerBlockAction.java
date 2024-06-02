package com.jtprince.coordinateoffset.offsetter.server;

import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerBlockAction;
import com.jtprince.coordinateoffset.Offset;
import com.jtprince.coordinateoffset.offsetter.packet.PacketSendOffsetter;

public class OffsetterServerBlockAction extends PacketSendOffsetter {
    public OffsetterServerBlockAction() {
        super(PacketType.Play.Server.BLOCK_ACTION);
    }

    @Override
    public void offset(PacketSendEvent event, Offset offset) {
        WrapperPlayServerBlockAction packet = new WrapperPlayServerBlockAction(event);
        packet.setBlockPosition(apply(packet.getBlockPosition(), offset));
    }
}
