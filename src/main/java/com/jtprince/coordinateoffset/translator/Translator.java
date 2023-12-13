package com.jtprince.coordinateoffset.translator;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.utility.MinecraftVersion;
import com.jtprince.coordinateoffset.Offset;
import com.jtprince.coordinateoffset.translator.R1_17.TranslatorClientboundR1_17;
import com.jtprince.coordinateoffset.translator.R1_17.TranslatorServerboundR1_17;
import com.jtprince.coordinateoffset.translator.R1_18.TranslatorClientboundR1_18;
import com.jtprince.coordinateoffset.translator.R1_18.TranslatorServerboundR1_18;
import com.jtprince.coordinateoffset.translator.R1_19.TranslatorClientboundR1_19;
import com.jtprince.coordinateoffset.translator.R1_19.TranslatorServerboundR1_19;
import com.jtprince.coordinateoffset.translator.R1_19_3.TranslatorClientboundR1_19_3;
import com.jtprince.coordinateoffset.translator.R1_19_3.TranslatorServerboundR1_19_3;
import com.jtprince.coordinateoffset.translator.R1_20_2.TranslatorClientboundR1_20_2;
import com.jtprince.coordinateoffset.translator.R1_20_2.TranslatorServerboundR1_20_2;
import com.jtprince.coordinateoffset.translator.R1_20_3.TranslatorClientboundR1_20_3;
import com.jtprince.coordinateoffset.translator.R1_20_3.TranslatorServerboundR1_20_3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Set;

/**
 * A Translator provides packet offsetting via ProtocolLib for a specific range of supported Minecraft versions.
 * Updating the plugin to a new Minecraft version should ideally only require writing a new Translator.
 */
public abstract class Translator {

    /**
     * A container for Translators that support a specific range of supported Minecraft versions.
     * @param minVersion The earliest ProtocolLib Minecraft version that these Translators support.
     * @param statedVersionRange Description of the Minecraft versions this Translator works with. (This should ONLY be
     *                           used for informational purposes)
     * @param clientbound A Translator for clientbound ("Server") packets.
     * @param serverbound A Translator for serverbound ("Client") packets.
     */
    public record Version(
            MinecraftVersion minVersion,
            @NotNull String statedVersionRange,
            Class<? extends Translator.Clientbound> clientbound,
            Class<? extends Translator.Serverbound> serverbound
    ) {}

    /*
     * On ProtocolLib update, validate that CoordinateOffset behaves correctly and update this to the newest ProtocolLib
     * Minecraft Version when it does. The plugin may be used on a version past this and work in many circumstances, but
     * it will print a warning that the protocol may have changed.
     */
    public static final MinecraftVersion LATEST_SUPPORTED = MinecraftVersion.v1_20_4;
    /*
     * Add new translator versions here, most recently released Minecraft versions first.
     * If a translator works for multiple Minecraft versions, set its upper bound as the second argument.
     */
    public static final List<Version> VERSIONS = List.of(
            new Translator.Version(MinecraftVersion.v1_20_4, "1.20.3 through 1.20.4",
                    TranslatorClientboundR1_20_3.class, TranslatorServerboundR1_20_3.class),
            new Translator.Version(MinecraftVersion.CONFIG_PHASE_PROTOCOL_UPDATE, "1.20.2",
                    TranslatorClientboundR1_20_2.class, TranslatorServerboundR1_20_2.class),
            new Translator.Version(MinecraftVersion.FEATURE_PREVIEW_UPDATE, "1.19.3 through 1.20.1",
                    TranslatorClientboundR1_19_3.class, TranslatorServerboundR1_19_3.class),
            new Translator.Version(MinecraftVersion.WILD_UPDATE, "1.19 through 1.19.2",
                    TranslatorClientboundR1_19.class, TranslatorServerboundR1_19.class),
            new Translator.Version(MinecraftVersion.CAVES_CLIFFS_2, "1.18.x",
                    TranslatorClientboundR1_18.class, TranslatorServerboundR1_18.class),
            new Translator.Version(MinecraftVersion.CAVES_CLIFFS_1, "1.17.x",
                    TranslatorClientboundR1_17.class, TranslatorServerboundR1_17.class)
    );

    /**
     * Get a list of all packet types that this translator needs to be able to translate.
     */
    public abstract @NotNull Set<PacketType> getPacketTypes();

    /**
     * Perform translation on a specific packet, applying the provided offset.
     * @return The packet modified with the given offset, or null if this packet should not be sent at all.
     */
    public abstract @Nullable PacketContainer translate(@NotNull PacketEvent packetEvent, @NotNull final Offset offset);

    public static abstract class Clientbound extends Translator {}

    public static abstract class Serverbound extends Translator {}
}
