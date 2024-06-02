package com.jtprince.coordinateoffset.offsetter.server;

import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerMultiBlockChange;
import com.jtprince.coordinateoffset.Offset;
import com.jtprince.coordinateoffset.offsetter.packet.PacketSendOffsetter;

public class OffsetterServerMultiBlockChange extends PacketSendOffsetter {
    public OffsetterServerMultiBlockChange() {
        super(PacketType.Play.Server.MULTI_BLOCK_CHANGE);
    }

    @Override
    public void offset(PacketSendEvent event, Offset offset) {
        WrapperPlayServerMultiBlockChange packet = new WrapperPlayServerMultiBlockChange(event);
        packet.setChunkPosition(applyChunk(packet.getChunkPosition(), offset));
    }
}
