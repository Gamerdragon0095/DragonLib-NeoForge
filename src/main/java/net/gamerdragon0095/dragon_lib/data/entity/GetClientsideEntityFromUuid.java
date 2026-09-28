package net.gamerdragon0095.dragon_lib.data.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import java.util.UUID;

public class GetClientsideEntityFromUuid {
    public static Entity getEntity(UUID entityUuid) {
        // Get the client-side level instance
        var level = Minecraft.getInstance().level;
        if (level == null) return null;

        // Loop through all entities currently tracked/loaded by the client
        for (Entity entity : level.entitiesForRendering()) {
            if (entity.getUUID().equals(entityUuid)) {
                return entity;
            }
        }
        return null;
    }
}
