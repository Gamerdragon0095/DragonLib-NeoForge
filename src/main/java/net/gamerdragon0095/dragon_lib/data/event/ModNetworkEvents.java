package net.gamerdragon0095.dragon_lib.data.event;

import net.gamerdragon0095.dragon_lib.data.network.SyncRenderingEntitiesPayload;
import net.gamerdragon0095.dragon_lib.data.storage.RenderingEntityManager;
import net.gamerdragon0095.dragon_lib.data.client.ClientDataCache;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(modid = "dragon_lib")
public class ModNetworkEvents {

    @SubscribeEvent
    public static void onRegisterPayloads(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar("1.0.0");

        // Define direction: Server to Client handling execution
        registrar.playToClient(
                SyncRenderingEntitiesPayload.TYPE,
                SyncRenderingEntitiesPayload.STREAM_CODEC,
                (payload, context) -> context.enqueueWork(() -> {
                    // Safely updates client cache without thread-blocking
                    ClientDataCache.CLIENT_TRACKED_ENTITIES.clear();
                    ClientDataCache.CLIENT_TRACKED_ENTITIES.addAll(payload.trackedEntities());
                })
        );
    }
}