package net.gamerdragon0095.dragon_lib.data.event;

import net.gamerdragon0095.dragon_lib.action.entity.GetEntityPlayerIsLookingAt;
import net.gamerdragon0095.dragon_lib.data.network.SyncRenderingEntitiesPayload;
import net.gamerdragon0095.dragon_lib.data.storage.RenderingEntityManager;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.pig.Pig;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.UUID;

@EventBusSubscriber(modid = "dragon_lib")
public class GameForgeEvents {

    // Automatically synchronizes state when a player links up/joins the world save
    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            syncDataToClient(player);
        }
    }

    // Helper utility to safely force client updates during live mutations
    public static void syncDataToClient(ServerPlayer player) {
        RenderingEntityManager manager = RenderingEntityManager.get((ServerLevel) player.level());
        var list = manager.getTrackedEntities(player.getUUID());

        // Push the update over the wire
        PacketDistributor.sendToPlayer(player, new SyncRenderingEntitiesPayload(list));
    }


    @SubscribeEvent
    public static void onPlayerLoggedOut(PlayerEvent.PlayerLoggedOutEvent event) {

        if (event.getEntity() instanceof ServerPlayer player) {
            ServerLevel level = player.level();
            RenderingEntityManager manager = RenderingEntityManager.get(level);

            UUID playerUuid = player.getUUID();

            java.util.List<UUID> renderList = manager.getTrackedEntities(playerUuid);

            if (!renderList.isEmpty()) {
                java.util.List<UUID> renderListIterator = new java.util.ArrayList<>(renderList);

                for (UUID entityUuid : renderListIterator) {

                    java.util.List<java.util.UUID> trackedList = manager.getTrackedEntities(playerUuid);
                    if (!trackedList.isEmpty() && trackedList.contains(entityUuid)) {
                        //System.out.println("entity is already in list");

                        manager.removeTrackedEntity(playerUuid, entityUuid);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onEntityTick(EntityTickEvent.Pre event) {
        /*Entity entity = event.getEntity();

        // 1. Performance Critical Sided Check: Only run data saving on the Server Side
        if (entity.level().isClientSide()) {
            return;
        } else {
            System.out.println("level is serverside");

            ServerLevel level = (ServerLevel) entity.level();

            // 2. Filter out entities you don't care about (Example: Only look at Pigs)

            // 3. Performance Check: Do not waste CPU checking an entity that is already tracked!
            RenderingEntityManager manager = RenderingEntityManager.get(level);

            // Find the nearest player within a specific radius (e.g., 10 blocks) to assign the tracked entity to
            for (ServerPlayer onlinePlayer : level.getServer().getPlayerList().getPlayers()) {

                // OPTIONAL DISTANCE CHECK: Only track if this specific online player is close enough
                // (e.g., within 32 blocks of this ticking entity and in the same dimension)
                if (onlinePlayer.level() != level || onlinePlayer.distanceToSqr(entity) > (32 * 32)) {
                    continue; // Skip this player, they are too far away or in another dimension
                }

                // If no player is nearby, we skip tracking for this tick
                if (onlinePlayer == null) {
                    System.out.println("nearestPlayer is null");
                    return;
                }

                UUID playerUuid = onlinePlayer.getUUID();
                UUID entityUuid = entity.getUUID();

                java.util.List<java.util.UUID> trackedList = manager.getTrackedEntities(playerUuid);
                if (!trackedList.isEmpty() && trackedList.contains(entityUuid)) {
                    System.out.println("entity is already in list");
                    return;
                }

                if (entity.getCustomName() != null) {
                    if (entity.getCustomName().getString().equalsIgnoreCase("Invisible")) {
                        System.out.println("entity has NOW been put in list");

                        // Write data to our master overworld dat file
                        manager.addTrackedEntity(playerUuid, entityUuid);

                        // Sync the updated collection down to the player's client-side cache
                        GameForgeEvents.syncDataToClient(onlinePlayer);
                    }
                }*/

                //Entity lookedAtEntity = GetEntityPlayerIsLookingAt.server(level.getServer(), playerUuid);

                // Check if the manager already contains this mapping to avoid redundant saving & syncing packets
                /*if (lookedAtEntity != null) {
                    java.util.List<java.util.UUID> trackedList = manager.getTrackedEntities(playerUuid);
                    if (!trackedList.isEmpty() && trackedList.contains(entityUuid)) {
                        System.out.println("entity is already in list");
                        if (lookedAtEntity != entity) {
                            manager.removeTrackedEntity(playerUuid, entityUuid);

                            GameForgeEvents.syncDataToClient(onlinePlayer);
                        }
                        return;
                    } else {
                        if (entity.getCustomName() != null) {
                            if ((entity.getCustomName().getString().equalsIgnoreCase("Invisible")) && (lookedAtEntity == entity)) {
                                System.out.println("entity has NOW been put in list");

                                // Write data to our master overworld dat file
                                manager.addTrackedEntity(playerUuid, entityUuid);

                                // Sync the updated collection down to the player's client-side cache
                                GameForgeEvents.syncDataToClient(onlinePlayer);
                            }
                        }
                    }
                } else {
                    java.util.List<java.util.UUID> trackedList = manager.getTrackedEntities(playerUuid);
                    if (!trackedList.isEmpty() && trackedList.contains(entityUuid)) {
                        System.out.println("entity is already in list");
                        manager.removeTrackedEntity(playerUuid, entityUuid);

                        GameForgeEvents.syncDataToClient(onlinePlayer);
                        return;
                    }
                }*/

                // 4. Mutation condition (Example: Only track the pig if it has a custom name or specific health)

            //}
        //}
    }
}