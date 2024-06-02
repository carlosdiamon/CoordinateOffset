package com.jtprince.coordinateoffset.offsetter.server;

import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.jtprince.coordinateoffset.Offset;
import com.jtprince.coordinateoffset.offsetter.packet.PacketSendOffsetter;
import com.jtprince.coordinateoffset.offsetter.wrapper.WrapperPlayServerUpdateLight;

public class OffsetterServerUpdateLight extends PacketSendOffsetter {
    public OffsetterServerUpdateLight() {
        super(PacketType.Play.Server.UPDATE_LIGHT);
    }

    @Override
    public void offset(PacketSendEvent event, Offset offset) {
        WrapperPlayServerUpdateLight packet = new WrapperPlayServerUpdateLight(event);
        packet.setChunkX(applyChunkX(packet.getChunkX(), offset));
        packet.setChunkZ(applyChunkZ(packet.getChunkZ(), offset));
    }
}
