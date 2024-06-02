package com.jtprince.coordinateoffset.offsetter.server;

import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerOpenSignEditor;
import com.jtprince.coordinateoffset.Offset;
import com.jtprince.coordinateoffset.offsetter.packet.PacketSendOffsetter;

public class OffsetterServerOpenSignEditor extends PacketSendOffsetter {
    public OffsetterServerOpenSignEditor() {
        super(PacketType.Play.Server.OPEN_SIGN_EDITOR);
    }
    @Override
    public void offset(PacketSendEvent event, Offset offset) {
        WrapperPlayServerOpenSignEditor packet = new WrapperPlayServerOpenSignEditor(event);
        packet.setPosition(apply(packet.getPosition(), offset));
    }
}
