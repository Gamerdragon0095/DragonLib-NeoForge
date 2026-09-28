package net.gamerdragon0095.dragon_lib.action.entity;

import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.PathfinderMob;

import java.util.Calendar;

public class ScareTargetAsEntity {

    public static void execute0(Entity target, Entity entity, double distance) {

        //System.out.println("entity has been scared");
        double coolDown = 0;

        if (target == null || entity == null) {
            if (target == null) {
                System.out.println("[" + (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + ":" + Calendar.getInstance().get(Calendar.MINUTE) + ":" + Calendar.getInstance().get(Calendar.SECOND)) + "] [DragonLib] Error: Provided variable 'target' of scareTargetAsEntity.execute0() is null");
            }

            if (entity == null) {
                System.out.println("[" + (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + ":" + Calendar.getInstance().get(Calendar.MINUTE) + ":" + Calendar.getInstance().get(Calendar.SECOND)) + "] [DragonLib] Error: Provided variable 'entity' of scareTargetAsEntity.execute0() is null");
            }
            return;
        }

        if (entity.getPersistentData().getDoubleOr("scareCoolDown", -1) <= 0) {

            if (distance == 0) {
                distance = (Mth.nextFloat(RandomSource.create(), 1.5f, 2f));
            }

            if (target instanceof PathfinderMob _target) {

                entity.getPersistentData().putDouble("scareCoolDown", (int) (20 * coolDown));

                _target.getPersistentData().putDouble("scaredTimer", 4 * distance);
                _target.getPersistentData().putDouble("scaredOfEntity", entity.getId());
                _target.getPersistentData().putDouble("freezeTime", 0);
                if (entity instanceof PathfinderMob _entity) {
                    _entity.lookAt(_target, 100, 100);
                }

            }
        }
    }

    public static void execute1(Entity target, Entity entity, double distance, double freezeTimer) {

        if (target == null || entity == null)
            return;

        double coolDown = 0;

        if (entity.getPersistentData().getDoubleOr("scareCoolDown", -1) <= 0) {

            if (distance == 0) {
                distance = (Mth.nextFloat(RandomSource.create(), 1.5f, 2f));
            }

            if (target instanceof PathfinderMob _target) {

                entity.getPersistentData().putDouble("scareCoolDown", (int) (20 * coolDown));

                _target.getPersistentData().putDouble("scaredTimer", 4 * distance);
                _target.getPersistentData().putDouble("scaredOfEntity", entity.getId());
                _target.getPersistentData().putDouble("freezeTime", freezeTimer);
                if (entity instanceof PathfinderMob _entity) {
                    _entity.lookAt(_target, 100, 100);
                }

            }
        }
    }

    public static void execute2(Entity target, Entity entity, double distance, double freezeTimer, double coolDown) {

        if (target == null || entity == null)
            return;

        if (entity.getPersistentData().getDoubleOr("scareCoolDown", -1) <= 0) {

            if (distance == 0) {
                distance = (Mth.nextFloat(RandomSource.create(), 1.5f, 2f));
            }

            if (target instanceof PathfinderMob _target) {

                entity.getPersistentData().putDouble("scareCoolDown", (int) (20 * coolDown));

                _target.getPersistentData().putDouble("scaredTimer", 4 * distance);
                _target.getPersistentData().putDouble("scaredOfEntity", entity.getId());
                _target.getPersistentData().putDouble("freezeTime", freezeTimer);
                if (entity instanceof PathfinderMob _entity) {
                    _entity.lookAt(_target, 100, 100);
                }

            }
        }
    }

}

