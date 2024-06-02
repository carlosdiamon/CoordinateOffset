package com.jtprince.coordinateoffset.offsetter.client;

import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientCreativeInventoryAction;
import com.jtprince.coordinateoffset.Offset;
import com.jtprince.coordinateoffset.offsetter.packet.PacketReceiveOffsetter;
import com.jtprince.util.PacketUtil;

public class OffsetterClientCreativeInventoryAction extends PacketReceiveOffsetter {
    public OffsetterClientCreativeInventoryAction() {
        super(PacketType.Play.Client.CREATIVE_INVENTORY_ACTION);
    }

    @Override
    public void offset(PacketReceiveEvent event, Offset offset) {
        WrapperPlayClientCreativeInventoryAction packet = new WrapperPlayClientCreativeInventoryAction(event);
        packet.setItemStack(PacketUtil.unapplyItemStack(packet.getItemStack(), offset));
    }
}
