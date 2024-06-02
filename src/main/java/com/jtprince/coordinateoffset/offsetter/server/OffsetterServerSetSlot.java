package com.jtprince.coordinateoffset.offsetter.server;

import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerSetSlot;
import com.jtprince.coordinateoffset.Offset;
import com.jtprince.coordinateoffset.offsetter.packet.PacketSendOffsetter;
import com.jtprince.util.PacketUtil;

public class OffsetterServerSetSlot extends PacketSendOffsetter {
    public OffsetterServerSetSlot() {
        super(PacketType.Play.Server.SET_SLOT);
    }

    @Override
    public void offset(PacketSendEvent event, Offset offset) {
        WrapperPlayServerSetSlot packet = new WrapperPlayServerSetSlot(event);
        packet.setItem(PacketUtil.applyItemStack(packet.getItem(), offset));
    }
}
