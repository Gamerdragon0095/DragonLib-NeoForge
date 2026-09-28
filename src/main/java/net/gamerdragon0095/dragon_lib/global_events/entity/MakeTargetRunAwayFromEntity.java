package net.gamerdragon0095.dragon_lib.global_events.entity;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

import javax.annotation.Nullable;

@EventBusSubscriber
public class MakeTargetRunAwayFromEntity {
    @SubscribeEvent
    public static void onEntityTick(EntityTickEvent.Pre event) {
        execute(event, event.getEntity().getY(), event.getEntity());
    }

    public static void execute(double y, Entity entity) {
        execute(null, y, entity);
    }

    private static void execute(@Nullable Event event, double y, Entity entity) {
        if (entity == null)
            return;

        double entityID_double = entity.getPersistentData().getDoubleOr("scaredOfEntity", -1);
        int entityID_int = (int) entityID_double;

        Entity sourceEntity = entity.level().getEntity(entityID_int);
        double freezeTime = entity.getPersistentData().getDoubleOr("freezeTime", -1);

        if (sourceEntity == null) {
            if (entity.getPersistentData().getDoubleOr("scaredOfEntity", 0) != 0) {
                if (entity instanceof PathfinderMob _entity) {
                    _entity.setSprinting(false);
                    _entity.getPersistentData().putDouble("scaredOfEntity", 0);
                }
            }
        } else {

            if (freezeTime > 0) {
                if (entity instanceof PathfinderMob _entity) {
                    if (sourceEntity instanceof PathfinderMob _sourceEntity) {
                        _sourceEntity.lookAt(_entity, 100, 100);
                        _sourceEntity.stopInPlace();
                    }
                    _entity.lookAt(sourceEntity, 100, 100);
                    _entity.stopInPlace();
                    _entity.getPersistentData().putDouble("freezeTime", freezeTime - 1);
                }
            } else {
                double scaredTimer = entity.getPersistentData().getDoubleOr("scaredTimer", 0);

                double targetX = entity.getX();
                double targetZ = entity.getZ();
                double entityX = sourceEntity.getX();
                double entityZ = sourceEntity.getZ();
                double finalX = 0;
                double finalZ = 0;
                double directionX = 0;
                double directionZ = 0;

                directionX = (entityX - targetX) / Math.abs(entityX - targetX);
                directionZ = (entityZ - targetZ) / Math.abs(entityZ - targetZ);
                finalX = targetX + ((-1) / directionX);
                finalZ = targetZ + ((-1) / directionZ);

                if (scaredTimer > 0) {
                    if (entity instanceof PathfinderMob _entity) {
                        _entity.getPersistentData().putDouble("scaredTimer", scaredTimer - 1);
                        _entity.getNavigation().moveTo(finalX, y, finalZ, 1);
                        _entity.setSprinting(true);
                        if (_entity.getTarget() == sourceEntity) {
                            _entity.setTarget(null);
                        }
                    }
                } else {
                    if (entity instanceof PathfinderMob _entity) {
                        if (_entity.isSprinting()) {
                            _entity.setSprinting(false);
                        }
                        if (_entity.getPersistentData().getDoubleOr("scaredOfEntity", 0) != 0) {
                            _entity.getPersistentData().putDouble("scaredOfEntity", 0);
                        }
                    }

                }
            }

            Entity scaredOfEntity = sourceEntity;

            if (entity instanceof Mob _entity) {
                LivingEntity targetEntity = null;
                if (_entity.getLastHurtByMob() != null){
                    targetEntity = _entity.getLastHurtByMob();
                } else {
                    targetEntity = _entity.getTarget();
                }

                if (scaredOfEntity == null)
                    return;
                if (scaredOfEntity instanceof LivingEntity _scaredOfEntity && targetEntity instanceof LivingEntity _targetEntity) {
                    if (_targetEntity == _scaredOfEntity) {

                        if (_entity.getLastHurtByMob() == _scaredOfEntity){
                            _entity.setLastHurtByMob(null);
                        } else {
                            _entity.setTarget(null);
                        }

                    }
                }

            }



        }
    }
}
