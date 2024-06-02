package com.jtprince.coordinateoffset.offsetter.server;

import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerSpawnExperienceOrb;
import com.jtprince.coordinateoffset.Offset;
import com.jtprince.coordinateoffset.offsetter.packet.PacketSendOffsetter;

public class OffsetterServerSpawnExperienceOrb extends PacketSendOffsetter {
    public OffsetterServerSpawnExperienceOrb() {
        super(PacketType.Play.Server.SPAWN_EXPERIENCE_ORB);
    }

    @Override
    public void offset(PacketSendEvent event, Offset offset) {
        WrapperPlayServerSpawnExperienceOrb packet = new WrapperPlayServerSpawnExperienceOrb(event);
        packet.setX(applyX(packet.getX(), offset));
        packet.setZ(applyZ(packet.getZ(), offset));
    }
}
