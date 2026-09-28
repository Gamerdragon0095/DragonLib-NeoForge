package net.gamerdragon0095.dragon_lib.data.network;

import net.minecraft.core.UUIDUtil;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import java.util.List;
import java.util.UUID;

public record SyncRenderingEntitiesPayload(List<UUID> trackedEntities) implements CustomPacketPayload {

    public static final Type<SyncRenderingEntitiesPayload> TYPE =
            new Type<>(Identifier.fromNamespaceAndPath("dragon_lib", "sync_unrendered_entities"));

    // Codec translating a Java list of UUIDs over a packet stream
    public static final StreamCodec<RegistryFriendlyByteBuf, SyncRenderingEntitiesPayload> STREAM_CODEC =
            UUIDUtil.STREAM_CODEC
                    .apply(ByteBufCodecs.list())
                    .<RegistryFriendlyByteBuf>cast() // CRITICAL FIX: Casts the buffer type upward
                    .map(SyncRenderingEntitiesPayload::new, SyncRenderingEntitiesPayload::trackedEntities);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
