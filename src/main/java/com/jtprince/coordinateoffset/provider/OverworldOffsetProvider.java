package com.jtprince.coordinateoffset.provider;

import com.jtprince.coordinateoffset.Offset;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * Abstract offset provider that simplifies Offset implementation for Vanilla-like servers by allowing the provider to
 * provide a single Offset for the player's Overworld rather than per-player/per-world. Each world's offset is derived
 * from the provided Offset based on the {@link org.bukkit.World.Environment} of the world:
 * <ul>
 *     <li>In NORMAL and CUSTOM environments, the offset returned by <code>getOverworldOffset</code> is used as-is.</li>
 *     <li>In NETHER environments, the offset coordinates are divided by 8 to match the Vanilla scaling of the
 *     Nether.</li>
 *     <li>In THE_END environments, there is <b>no offset</b> because the End has a distinctive coordinate system.</li>
 * </ul>
 * <p>
 * For more flexibility in controlling the Offsets per-world, consider implementing {@link OffsetProvider} instead.
 */
public abstract class OverworldOffsetProvider implements OffsetProvider {
    @Override
    public final @NotNull Offset getOffset(@NotNull Player player, @NotNull World world) {
        switch (world.getEnvironment()) {
            case NORMAL, CUSTOM -> { return getOverworldOffset(player); }
            case NETHER -> { return getOverworldOffset(player).toNetherOffset(); }
            case THE_END -> { return Offset.ZERO; }
        }

        throw new IllegalArgumentException("Unknown world environment for world " + world.getName());
    }

    public abstract @NotNull Offset getOverworldOffset(@NotNull Player player);
}
