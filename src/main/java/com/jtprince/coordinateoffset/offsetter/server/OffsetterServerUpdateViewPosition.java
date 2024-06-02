package com.jtprince.coordinateoffset.offsetter.server;

import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerUpdateViewPosition;
import com.jtprince.coordinateoffset.Offset;
import com.jtprince.coordinateoffset.offsetter.packet.PacketSendOffsetter;

public class OffsetterServerUpdateViewPosition extends PacketSendOffsetter {
    public OffsetterServerUpdateViewPosition() {
        super(PacketType.Play.Server.UPDATE_VIEW_POSITION);
    }

    @Override
    public void offset(PacketSendEvent event, Offset offset) {
        WrapperPlayServerUpdateViewPosition packet = new WrapperPlayServerUpdateViewPosition(event);
        packet.setChunkX(applyChunkX(packet.getChunkX(), offset));
        packet.setChunkZ(applyChunkZ(packet.getChunkZ(), offset));
    }
}
