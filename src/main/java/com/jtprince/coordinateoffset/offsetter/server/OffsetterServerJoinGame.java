package com.jtprince.coordinateoffset.offsetter.server;

import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerJoinGame;
import com.jtprince.coordinateoffset.Offset;
import com.jtprince.coordinateoffset.offsetter.packet.PacketSendOffsetter;
import com.jtprince.util.PacketUtil;

public class OffsetterServerJoinGame extends PacketSendOffsetter {
    public OffsetterServerJoinGame() {
        super(PacketType.Play.Server.JOIN_GAME);
    }

    @Override
    public void offset(PacketSendEvent event, Offset offset) {
        WrapperPlayServerJoinGame packet = new WrapperPlayServerJoinGame(event);
        if (packet.getLastDeathPosition() != null) {
            packet.setLastDeathPosition(PacketUtil.applyWorldBlock(packet.getLastDeathPosition(), offset));
        }
    }
}
