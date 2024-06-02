package com.jtprince.coordinateoffset.offsetter.server;

import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerChunkData;
import com.jtprince.coordinateoffset.Offset;
import com.jtprince.coordinateoffset.offsetter.packet.PacketSendOffsetter;

public class OffsetterServerChunkData extends PacketSendOffsetter {
    public OffsetterServerChunkData() {
        super(PacketType.Play.Server.CHUNK_DATA);
    }
    @Override
    public void offset(PacketSendEvent event, Offset offset) {
        WrapperPlayServerChunkData packet = new WrapperPlayServerChunkData(event);
        packet.setColumn(applyColumn(packet.getColumn(), offset, event.getUser()));
    }
}
