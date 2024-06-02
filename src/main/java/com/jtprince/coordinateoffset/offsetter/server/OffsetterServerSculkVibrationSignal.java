package com.jtprince.coordinateoffset.offsetter.server;

import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.jtprince.coordinateoffset.Offset;
import com.jtprince.coordinateoffset.offsetter.packet.PacketSendOffsetter;
import com.jtprince.coordinateoffset.offsetter.wrapper.WrapperPlayServerSculkVibrationSignal;

public class OffsetterServerSculkVibrationSignal extends PacketSendOffsetter {
    public OffsetterServerSculkVibrationSignal() {
        // Removed in 1.19
        super(PacketType.Play.Server.SCULK_VIBRATION_SIGNAL);
    }

    @Override
    public void offset(PacketSendEvent event, Offset offset) {
        WrapperPlayServerSculkVibrationSignal packet = new WrapperPlayServerSculkVibrationSignal(event);
        packet.setSourcePosition(apply(packet.getSourcePosition(), offset));
        if (packet.getDestinationPosition() != null) {
            packet.setDestinationPosition(apply(packet.getDestinationPosition(), offset));
        }
    }
}
