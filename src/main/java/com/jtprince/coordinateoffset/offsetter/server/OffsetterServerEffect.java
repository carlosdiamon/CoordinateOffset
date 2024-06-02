package com.jtprince.coordinateoffset.offsetter.server;

import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.jtprince.coordinateoffset.Offset;
import com.jtprince.coordinateoffset.offsetter.packet.PacketSendOffsetter;
import com.jtprince.coordinateoffset.offsetter.wrapper.WrapperPlayServerEffect;

public class OffsetterServerEffect extends PacketSendOffsetter {
    public OffsetterServerEffect() {
        super(PacketType.Play.Server.EFFECT);
    }

    @Override
    public void offset(PacketSendEvent event, Offset offset) {
        WrapperPlayServerEffect packet = new WrapperPlayServerEffect(event);
        packet.setPosition(apply(packet.getPosition(), offset));
    }
}
