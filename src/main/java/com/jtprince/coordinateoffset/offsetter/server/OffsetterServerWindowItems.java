package com.jtprince.coordinateoffset.offsetter.server;

import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerWindowItems;
import com.jtprince.coordinateoffset.Offset;
import com.jtprince.coordinateoffset.offsetter.packet.PacketSendOffsetter;
import com.jtprince.util.PacketUtil;

public class OffsetterServerWindowItems extends PacketSendOffsetter {
    public OffsetterServerWindowItems() {
        super(PacketType.Play.Server.WINDOW_ITEMS);
    }

    @Override
    public void offset(PacketSendEvent event, Offset offset) {
        WrapperPlayServerWindowItems packet = new WrapperPlayServerWindowItems(event);
        packet.setItems(packet.getItems().stream().map(it -> PacketUtil.applyItemStack(it, offset)).toList());
        if (packet.getCarriedItem().isPresent()) {
            packet.setCarriedItem(PacketUtil.applyItemStack(packet.getCarriedItem().get(), offset));
        }
    }
}
