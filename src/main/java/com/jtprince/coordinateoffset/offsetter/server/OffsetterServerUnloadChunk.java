package com.jtprince.coordinateoffset.offsetter.server;

import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerUnloadChunk;
import com.jtprince.coordinateoffset.Offset;
import com.jtprince.coordinateoffset.offsetter.packet.PacketSendOffsetter;

public class OffsetterServerUnloadChunk extends PacketSendOffsetter {
    public OffsetterServerUnloadChunk() {
        super(PacketType.Play.Server.UNLOAD_CHUNK);
    }

    @Override
    public void offset(PacketSendEvent event, Offset offset) {
        WrapperPlayServerUnloadChunk packet = new WrapperPlayServerUnloadChunk(event);
        packet.setChunkX(applyChunkX(packet.getChunkX(), offset));
        packet.setChunkZ(applyChunkZ(packet.getChunkZ(), offset));
    }
}
