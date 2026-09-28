package net.gamerdragon0095.dragon_lib.data.storage;

import com.mojang.serialization.Codec;
import net.minecraft.core.UUIDUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;
import java.util.*;

import net.minecraft.world.level.saveddata.SavedDataType;
import net.minecraft.resources.Identifier;

public class RenderingEntityManager extends SavedData {
    // Runtime runtime-safe data map
    private final Map<UUID, List<UUID>> playerTrackedMap = new HashMap<>();

    // Codec converting Map<UUID, List<UUID>> to standard NBT formats
    private static final Codec<Map<UUID, List<UUID>>> MAP_CODEC =
            Codec.unboundedMap(UUIDUtil.STRING_CODEC, UUIDUtil.CODEC.listOf());

    private static final Codec<RenderingEntityManager> MANAGER_CODEC = MAP_CODEC.xmap(
            rawMap -> {
                RenderingEntityManager manager = new RenderingEntityManager();
                manager.playerTrackedMap.putAll(rawMap);
                return manager;
            },
            manager -> manager.playerTrackedMap
    );

    public static final SavedDataType<RenderingEntityManager> TYPE = new SavedDataType<>(
            Identifier.fromNamespaceAndPath("dragon_lib", "unrendered_entities"),
            RenderingEntityManager::new,
            MANAGER_CODEC.fieldOf("DataMap").codec(), // Fits perfectly into the constructor
            null
    );

    // Global Access Method
    public static RenderingEntityManager get(ServerLevel level) {
        return level.getServer().overworld().getDataStorage().computeIfAbsent(TYPE);
    }

    // --- API Methods for you and other developers ---

    public List<UUID> getTrackedEntities(UUID playerUuid) {
        return Collections.unmodifiableList(playerTrackedMap.getOrDefault(playerUuid, Collections.emptyList()));
    }

    public void addTrackedEntity(UUID playerUuid, UUID entityUuid) {
        // 1. Grab the list, or create a brand new ArrayList if it doesn't exist
        List<UUID> originalList = playerTrackedMap.computeIfAbsent(playerUuid, k -> new ArrayList<>());

        // 2. CRITICAL FIX: If the list loaded by the Codec is immutable, wrap it in a mutable ArrayList
        if (!(originalList instanceof ArrayList<UUID>)) {
            originalList = new ArrayList<>(originalList);
            playerTrackedMap.put(playerUuid, originalList); // Update the map reference with the mutable version
        }

        // 3. Now we can safely perform our operations without any crashes
        if (!originalList.contains(entityUuid)) {
            originalList.add(entityUuid);
            this.setDirty(); // Tells Minecraft to save the changes to the disk
        }
    }

    public void removeTrackedEntity(UUID playerUuid, UUID entityUuid) {
        List<UUID> originalList = playerTrackedMap.get(playerUuid);

        // 1. If the player doesn't have any tracked entities recorded, do nothing
        if (originalList == null) {
            return;
        }

        // 2. CRITICAL FIX: If the loaded list is immutable, wrap it in a mutable ArrayList
        if (!(originalList instanceof ArrayList<UUID>)) {
            originalList = new ArrayList<>(originalList);
            playerTrackedMap.put(playerUuid, originalList); // Update the map reference with the mutable version
        }

        // 3. Now it is completely safe to remove the entity from the list
        if (originalList.remove(entityUuid)) {
            // Optional cleanup: If their tracked list is now empty, remove their entry entirely to keep files tiny
            if (originalList.isEmpty()) {
                playerTrackedMap.remove(playerUuid);
            }

            this.setDirty(); // Crucial: Tells Minecraft to save the removal changes to the disk file
        }
    }
}