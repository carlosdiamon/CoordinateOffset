package com.jtprince.coordinateoffset.offsetter.server;

import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.jtprince.coordinateoffset.Offset;
import com.jtprince.coordinateoffset.offsetter.packet.PacketSendOffsetter;
import com.jtprince.coordinateoffset.offsetter.wrapper.WrapperPlayServerSoundEffect_WithIdentifier;

public class OffsetterServerSoundEffect extends PacketSendOffsetter {
    public OffsetterServerSoundEffect() {
        super(PacketType.Play.Server.SOUND_EFFECT);
    }

    @Override
    public void offset(PacketSendEvent event, Offset offset) {
        WrapperPlayServerSoundEffect_WithIdentifier packet = new WrapperPlayServerSoundEffect_WithIdentifier(event);
        packet.setEffectPosition(applyTimes8(packet.getEffectPosition(), offset));
    }
}
