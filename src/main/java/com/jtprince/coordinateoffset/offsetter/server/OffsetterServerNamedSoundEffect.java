package com.jtprince.coordinateoffset.offsetter.server;

import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.jtprince.coordinateoffset.Offset;
import com.jtprince.coordinateoffset.offsetter.packet.PacketSendOffsetter;
import com.jtprince.coordinateoffset.offsetter.wrapper.WrapperPlayServerNamedSoundEffect;

public class OffsetterServerNamedSoundEffect extends PacketSendOffsetter {
    public OffsetterServerNamedSoundEffect() {
        // Removed around 1.19.2ish
        super(PacketType.Play.Server.NAMED_SOUND_EFFECT);
    }

    @Override
    public void offset(PacketSendEvent event, Offset offset) {
        WrapperPlayServerNamedSoundEffect packet = new WrapperPlayServerNamedSoundEffect(event);
        packet.setEffectPosition(applyTimes8(packet.getEffectPosition(), offset));
    }
}
