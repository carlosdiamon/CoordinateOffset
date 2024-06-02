package com.jtprince.coordinateoffset.offsetter.server;

import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.protocol.player.Equipment;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerEntityEquipment;
import com.jtprince.coordinateoffset.Offset;
import com.jtprince.coordinateoffset.offsetter.packet.PacketSendOffsetter;
import com.jtprince.util.PacketUtil;

public class OffsetterServerEntityEquipment extends PacketSendOffsetter {
    public OffsetterServerEntityEquipment() {
        super(PacketType.Play.Server.ENTITY_EQUIPMENT);
    }

    @Override
    public void offset(PacketSendEvent event, Offset offset) {
        WrapperPlayServerEntityEquipment packet = new WrapperPlayServerEntityEquipment(event);
        for (Equipment equipment : packet.getEquipment()) {
            equipment.setItem(PacketUtil.applyItemStack(equipment.getItem(), offset));
        }
    }
}
