package com.jtprince.coordinateoffset.translator.R1_19_3;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.jtprince.coordinateoffset.Offset;
import com.jtprince.coordinateoffset.translator.EntityMetadataUtils;
import com.jtprince.coordinateoffset.translator.PacketContainerUtils;
import com.jtprince.coordinateoffset.translator.Translator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;

/**
 * Translator for Minecraft 1.19.3, 1.19.4, 1.20, 1.20.1 (protocol 761-763)
 * <a href="https://wiki.vg/index.php?title=Protocol&oldid=18375">Wiki.vg</a>
 */
@SuppressWarnings({"DuplicatedCode", "deprecation", "RedundantSuppression"})
public class TranslatorClientboundR1_19_3 extends Translator.Clientbound {
    private final Map<PacketType, BiFunction<PacketContainer, Offset, PacketContainer>> translators = getTranslators();
    @Override
    public @NotNull Set<PacketType> getPacketTypes() {
        return translators.keySet();
    }

    @Override
    public @Nullable PacketContainer translate(@NotNull PacketEvent packetEvent, @NotNull Offset offset) {
        PacketContainer packet = packetEvent.getPacket();
        var translatorFunction = translators.get(packet.getType());
        if (translatorFunction != null) {
            /*
             * Deep clone: Outbound packets can contain nested objects that are reused when the packet gets sent to
             * successive players. That means that just editing values in the packet in-place would propagate the
             * edited values to all players, and cause lots of glitches (those glitches are only apparent with multiple
             * online players). It would be optimal to only deep-clone the packets that need this, but risky, since it
             * is not always obvious when a packet type will start reusing nested objects.
             */
            return translatorFunction.apply(packet.deepClone(), offset);
        } else {
            return packet;
        }
    }

    private Map<PacketType, BiFunction<PacketContainer, Offset, PacketContainer>> getTranslators() {
        Map<PacketType, BiFunction<PacketContainer, Offset, PacketContainer>> map = new HashMap<>();

        map.put(PacketType.Play.Server.SPAWN_ENTITY, PacketContainerUtils::sendDouble3D); // 0x01
        map.put(PacketType.Play.Server.SPAWN_ENTITY_EXPERIENCE_ORB, PacketContainerUtils::sendDouble3D); // 0x02
        map.put(PacketType.Play.Server.NAMED_ENTITY_SPAWN, PacketContainerUtils::sendDouble3D); // 0x03
        map.put(PacketType.Play.Server.BLOCK_BREAK_ANIMATION, PacketContainerUtils::sendBlockPosition); // 0x07
        map.put(PacketType.Play.Server.TILE_ENTITY_DATA, PacketContainerUtils::sendTileEntityNbt); // 0x08
        map.put(PacketType.Play.Server.BLOCK_ACTION, PacketContainerUtils::sendBlockPosition); // 0x09
        map.put(PacketType.Play.Server.BLOCK_CHANGE, PacketContainerUtils::sendBlockPosition); // 0x0A
        map.put(PacketType.Play.Server.WINDOW_ITEMS, PacketContainerUtils::sendItemStackList); // 0x12
        map.put(PacketType.Play.Server.SET_SLOT, PacketContainerUtils::sendItemStack); // 0x14
        map.put(PacketType.Play.Server.EXPLOSION, (pkt, offset) ->  // 0x1D
            PacketContainerUtils.sendBlockPositionCollection(PacketContainerUtils.sendDouble3D(pkt, offset), offset));
        map.put(PacketType.Play.Server.UNLOAD_CHUNK, PacketContainerUtils::sendChunkCoordinate); // 0x1E
        map.put(PacketType.Play.Server.MAP_CHUNK, PacketContainerUtils::sendChunkCoordinate); // 0x24
        map.put(PacketType.Play.Server.WORLD_EVENT, PacketContainerUtils::sendBlockPosition); // 0x25
        map.put(PacketType.Play.Server.WORLD_PARTICLES, PacketContainerUtils::sendParticle); // 0x26
        map.put(PacketType.Play.Server.LIGHT_UPDATE, PacketContainerUtils::sendChunkCoordinate); // 0x27
        map.put(PacketType.Play.Server.LOGIN, PacketContainerUtils::sendDeathLocation1_19); // 0x28
        map.put(PacketType.Play.Server.VEHICLE_MOVE, PacketContainerUtils::sendDouble3D); // 0x2F
        map.put(PacketType.Play.Server.OPEN_SIGN_EDITOR, PacketContainerUtils::sendBlockPosition); // 0x31
        map.put(PacketType.Play.Server.LOOK_AT, PacketContainerUtils::sendDouble3D); // 0x3B
        map.put(PacketType.Play.Server.POSITION, PacketContainerUtils::sendPossiblyRelativePosition); // 0x3C
        map.put(PacketType.Play.Server.RESPAWN, PacketContainerUtils::sendDeathLocation1_19); // 0x41
        map.put(PacketType.Play.Server.MULTI_BLOCK_CHANGE, PacketContainerUtils::sendSectionPosition); // 0x43
        map.put(PacketType.Play.Server.VIEW_CENTRE, PacketContainerUtils::sendChunkCoordinate); // 0x4E
        map.put(PacketType.Play.Server.SPAWN_POSITION, PacketContainerUtils::sendBlockPosition); // 0x50
        map.put(PacketType.Play.Server.ENTITY_METADATA, EntityMetadataUtils::sendEntityMetadata1_19_3); // 0x52
        map.put(PacketType.Play.Server.NAMED_SOUND_EFFECT, PacketContainerUtils::sendInt3DTimes8); // 0x62
        map.put(PacketType.Play.Server.ENTITY_TELEPORT, PacketContainerUtils::sendDouble3D); // 0x68

        return map;
    }
}
