package com.jtprince.coordinateoffset.offsetter.server;

import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerBlockBreakAnimation;
import com.jtprince.coordinateoffset.Offset;
import com.jtprince.coordinateoffset.offsetter.packet.PacketSendOffsetter;

public class OffsetterServerBlockBreakAnimation extends PacketSendOffsetter {
    public OffsetterServerBlockBreakAnimation() {
        super(PacketType.Play.Server.BLOCK_BREAK_ANIMATION);
    }

    @Override
    public void offset(PacketSendEvent event, Offset offset) {
        WrapperPlayServerBlockBreakAnimation packet = new WrapperPlayServerBlockBreakAnimation(event);
        packet.setBlockPosition(apply(packet.getBlockPosition(), offset));
    }
}
