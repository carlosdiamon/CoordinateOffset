package com.jtprince.coordinateoffset.translator.R1_19_4;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.jtprince.coordinateoffset.Offset;
import com.jtprince.coordinateoffset.translator.EntityMetadataUtils;
import com.jtprince.coordinateoffset.translator.PacketContainerUtils;
import com.jtprince.coordinateoffset.translator.Translator;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;

/**
 * Translator for versions 1.19.4, 1.20, 1.20.1
 * <a href="https://wiki.vg/index.php?title=Protocol&oldid=18375">Wiki.vg</a>
 */
public class TranslatorClientboundR1_19_4 extends Translator {
    private final Map<PacketType, BiFunction<PacketContainer, Offset, PacketContainer>> translators = getTranslators();
    @Override
    public @NotNull Set<PacketType> getPacketTypes() {
        return translators.keySet();
    }

    @Override
    public @NotNull PacketContainer translate(@NotNull PacketContainer packet, @NotNull Offset offset) {
        var translatorFunction = translators.get(packet.getType());
        if (translatorFunction != null) {
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
        map.put(PacketType.Play.Server.WORLD_PARTICLES, PacketContainerUtils::sendVibrationParticle); // 0x26
        map.put(PacketType.Play.Server.LIGHT_UPDATE, PacketContainerUtils::sendChunkCoordinate); // 0x27
        map.put(PacketType.Play.Server.LOGIN, PacketContainerUtils::sendDeathLocation); // 0x28
        map.put(PacketType.Play.Server.OPEN_SIGN_EDITOR, PacketContainerUtils::sendBlockPosition); // 0x31
        map.put(PacketType.Play.Server.POSITION, PacketContainerUtils::sendPossiblyRelativePosition); // 0x3C
        map.put(PacketType.Play.Server.RESPAWN, PacketContainerUtils::sendDeathLocation); // 0x41
        map.put(PacketType.Play.Server.MULTI_BLOCK_CHANGE, PacketContainerUtils::sendSectionPosition); // 0x43
        map.put(PacketType.Play.Server.VIEW_CENTRE, PacketContainerUtils::sendChunkCoordinate); // 0x4E
        map.put(PacketType.Play.Server.SPAWN_POSITION, PacketContainerUtils::sendBlockPosition); // 0x50
        map.put(PacketType.Play.Server.ENTITY_METADATA, EntityMetadataUtils::sendEntityMetadata); // 0x52
        map.put(PacketType.Play.Server.NAMED_SOUND_EFFECT, PacketContainerUtils::sendInt3DTimes8); // 0x62
        map.put(PacketType.Play.Server.ENTITY_TELEPORT, PacketContainerUtils::sendDouble3D); // 0x68

        map.put(PacketType.Play.Server.INITIALIZE_BORDER, PacketContainerUtils::sendDouble2D); // 0x22
        map.put(PacketType.Play.Server.SET_BORDER_CENTER, PacketContainerUtils::sendDouble2D); // 0x47

        return map;
    }

    /**
     * These packets contain nested objects that are reused when the packet gets sent to successive players.
     * That means that just editing values in the packet in-place would propagate the edited values to all
     * players, and cause lots of glitches (those glitches are only apparent with multiple online players).
     * Right now, this list is incomplete and unused, and instead ALL packets will be deep-cloned to be safe.
     */
    @SuppressWarnings("unused")
    private final Set<PacketType> deepClonePackets = Set.of(
            PacketType.Play.Server.TILE_ENTITY_DATA,
            PacketType.Play.Server.BLOCK_ACTION,
            PacketType.Play.Server.WORLD_PARTICLES,
            PacketType.Play.Server.MULTI_BLOCK_CHANGE,
            PacketType.Play.Server.ENTITY_METADATA,
            PacketType.Play.Server.NAMED_SOUND_EFFECT,
            PacketType.Play.Server.ENTITY_TELEPORT
    );
}
