package net.gamerdragon0095.dragon_lib.mixin;

import net.gamerdragon0095.dragon_lib.data.client.ClientDataCache;
import net.gamerdragon0095.dragon_lib.data.entity.GetClientsideEntityFromUuid;
import net.gamerdragon0095.dragon_lib.data.storage.RenderingEntityManager;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.pig.Pig;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.UUID;

@Mixin(EntityRenderer.class)
public abstract class EntityRendererMixin<T extends Entity, S extends EntityRenderState> {
    public EntityRendererMixin(Level level) {
        super();
    }

    @Inject(method = "shouldRender", at = @At(value = "HEAD"), cancellable = true)
    private void shouldRenderMixin(T entity, Frustum culler, double camX, double camY, double camZ, CallbackInfoReturnable<Boolean> info) {

        if (entity != null) {

            /*for (int i = 0; i < ClientDataCache.CLIENT_TRACKED_ENTITIES.lastIndexOf(ClientDataCache.CLIENT_TRACKED_ENTITIES.toArray()); i++) {
                UUID id = ClientDataCache.CLIENT_TRACKED_ENTITIES.get(i);
                Entity entity_ = GetClientsideEntityFromUuid.getEntity(id);

                if (entity_ == null) {

                }
            }*/

            if(!ClientDataCache.CLIENT_TRACKED_ENTITIES.contains(entity.getUUID())) {
                info.setReturnValue(true);
            } else {

                info.setReturnValue(false);
            }

        } else {

        }
    }
}
