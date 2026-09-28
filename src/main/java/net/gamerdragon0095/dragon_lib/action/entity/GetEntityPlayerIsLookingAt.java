package net.gamerdragon0095.dragon_lib.action.entity;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.entity.ai.attributes.Attributes;

import net.minecraft.client.Minecraft;
import net.minecraft.world.phys.HitResult;

import java.util.UUID;

public class GetEntityPlayerIsLookingAt {

    public static Entity server(MinecraftServer server, UUID playerUuid) {
        // 1. Find the online server player by UUID
        if ((server != null) && (playerUuid != null)) {
            ServerPlayer player = server.getPlayerList().getPlayer(playerUuid);
            if (player == null) {
                return null; // Player is offline or doesn't exist
            }

            // 2. Determine the player's reach distance (NeoForge attribute-based)
            double reachDistance = player.getAttributeValue(Attributes.ENTITY_INTERACTION_RANGE);

            // 3. Calculate the raycast vectors
            Vec3 eyePosition = player.getEyePosition(1.0F);
            Vec3 lookDirection = player.getViewVector(1.0F);
            Vec3 targetPosition = eyePosition.add(lookDirection.scale(reachDistance));

            // 4. Create a bounding box enclosing the entire ray path
            AABB boundingBox = player.getBoundingBox()
                    .expandTowards(lookDirection.scale(reachDistance))
                    .inflate(1.0D);

            // 5. Perform the raycast targeting entities
            EntityHitResult hitResult = ProjectileUtil.getEntityHitResult(
                    player,
                    eyePosition,
                    targetPosition,
                    boundingBox,
                    entity -> !entity.isSpectator() && entity.isPickable(), // Filter valid entities
                    reachDistance * reachDistance // Maximum distance squared
            );

            // 6. Return the entity if one was hit
            return hitResult != null ? hitResult.getEntity() : null;
        }
        return null;
    }

    public static Entity local() {
        Minecraft minecraft = Minecraft.getInstance();
        HitResult hitResult = minecraft.hitResult;

        // Ensure a hit actually occurred and it's an entity
        if (hitResult != null && hitResult.getType() == HitResult.Type.ENTITY) {
            EntityHitResult entityHitResult = (EntityHitResult) hitResult;
            return entityHitResult.getEntity();
        }

        return null; // Not looking at an entity
    }

}