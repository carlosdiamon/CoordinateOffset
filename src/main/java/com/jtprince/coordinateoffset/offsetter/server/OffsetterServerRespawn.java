package com.jtprince.coordinateoffset.offsetter.server;

import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerRespawn;
import com.jtprince.coordinateoffset.Offset;
import com.jtprince.coordinateoffset.offsetter.packet.PacketSendOffsetter;
import com.jtprince.util.PacketUtil;

public class OffsetterServerRespawn extends PacketSendOffsetter {
    public OffsetterServerRespawn() {
        super(PacketType.Play.Server.RESPAWN);
    }

    @Override
    public void offset(PacketSendEvent event, Offset offset) {
        WrapperPlayServerRespawn packet = new WrapperPlayServerRespawn(event);
        if (packet.getLastDeathPosition() != null) {
            packet.setLastDeathPosition(PacketUtil.applyWorldBlock(packet.getLastDeathPosition(), offset));
        }
    }
}
