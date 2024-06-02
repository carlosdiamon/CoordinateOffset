package com.jtprince.coordinateoffset.offsetter;

import com.github.retrooper.packetevents.protocol.packettype.PacketTypeCommon;
import com.jtprince.coordinateoffset.offsetter.client.*;
import com.jtprince.coordinateoffset.offsetter.packet.PacketReceiveOffsetter;
import com.jtprince.coordinateoffset.offsetter.packet.PacketSendOffsetter;
import com.jtprince.coordinateoffset.offsetter.server.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OffsetterRegistry {
    private final Map<PacketTypeCommon, PacketSendOffsetter> sendOffsetters;
    private final Map<PacketTypeCommon, PacketReceiveOffsetter> receiveOffsetters;

    public OffsetterRegistry() {
        this.sendOffsetters = new HashMap<>();
        this.receiveOffsetters = new HashMap<>();
    }

    public void init() {
        List<PacketSendOffsetter> sendOffsetters = List.of(
                new OffsetterServerAcknowledgePlayerDigging(),
                new OffsetterServerBlockAction(),
                new OffsetterServerBlockBreakAnimation(),
                new OffsetterServerBlockChange(),
                new OffsetterServerBlockEntityData(),
                new OffsetterServerChunkData(),
                new OffsetterServerEffect(),
                new OffsetterServerEntityEquipment(),
                new OffsetterServerEntityMetadata(),
                new OffsetterServerEntityTeleport(),
                new OffsetterServerExplosion(),
                new OffsetterServerFacePlayer(),
                new OffsetterServerJoinGame(),
                new OffsetterServerUpdateLight(),
                new OffsetterServerMultiBlockChange(),
                new OffsetterServerNamedSoundEffect(),
                new OffsetterServerOpenSignEditor(),
                new OffsetterServerParticle(),
                new OffsetterServerPlayerPositionAndLook(),
                new OffsetterServerRespawn(),
                new OffsetterServerSculkVibrationSignal(),
                new OffsetterServerSetSlot(),
                new OffsetterServerSoundEffect(),
                new OffsetterServerSpawnEntity(),
                new OffsetterServerSpawnExperienceOrb(),
                new OffsetterServerSpawnLivingEntity(),
                new OffsetterServerSpawnPainting(),
                new OffsetterServerSpawnPlayer(),
                new OffsetterServerSpawnPosition(),
                new OffsetterServerUnloadChunk(),
                new OffsetterServerUpdateViewPosition(),
                new OffsetterServerVehicleMove(),
                new OffsetterServerWindowItems()
        );
        List<PacketReceiveOffsetter> receiveOffsetters = List.of(
                new OffsetterClientClickWindow(),
                new OffsetterClientCreativeInventoryAction(),
                new OffsetterClientGenerateStructure(),
                new OffsetterClientPlayerBlockPlacement(),
                new OffsetterClientPlayerDigging(),
                new OffsetterClientPlayerPosition(),
                new OffsetterClientUpdateCommandBlock(),
                new OffsetterClientUpdateJigsawBlock(),
                new OffsetterClientUpdateSign(),
                new OffsetterClientVehicleMove()
        );


        for (PacketSendOffsetter offsetter : sendOffsetters) {
            for (PacketTypeCommon packetType : offsetter.getPacketTypes()) {
                this.sendOffsetters.put(packetType, offsetter);
            }
        }

        for (PacketReceiveOffsetter offsetter : receiveOffsetters) {
            for (PacketTypeCommon packetType : offsetter.getPacketTypes()) {
                this.receiveOffsetters.put(packetType, offsetter);
            }
        }

    }

    public PacketSendOffsetter getSendOffsetter(PacketTypeCommon packetType) {
        return this.sendOffsetters.get(packetType);
    }

    public PacketReceiveOffsetter getReceiveOffsetter(PacketTypeCommon packetType) {
        return this.receiveOffsetters.get(packetType);
    }
}
