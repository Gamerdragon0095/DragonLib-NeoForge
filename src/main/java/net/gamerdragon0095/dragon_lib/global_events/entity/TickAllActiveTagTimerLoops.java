package net.gamerdragon0095.dragon_lib.global_events.entity;

import net.gamerdragon0095.dragon_lib.action.entity.NBTTimerLoopAct;
import net.gamerdragon0095.dragon_lib.data.entity.NBTTimerLoopDat;
import net.minecraft.world.entity.Entity;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

import javax.annotation.Nullable;

@EventBusSubscriber(modid = "dragon_lib")
public class TickAllActiveTagTimerLoops {
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
        if (entity.getPersistentData().getStringOr("TimerLoops", "").length() >= 1) {

            //problem child maybe?..          ====================================================================\/\/\/\/
            String amountOfTimers = entity.getPersistentData().getStringOr("TimerLoops", "").substring(12, entity.getPersistentData().getStringOr("TimerLoops", "").indexOf(")"));

            int maxNumberOfTimers = new Object() {
                int convert(String s) {
                    try {
                        return Integer.parseInt(s.trim());
                    } catch (Exception e) {

                    }
                    return 0;
                }
            }.convert(amountOfTimers);

            int numberOfTimers = 0;
            int index0 = 0;
            int index1 = 0;
            int index2 = 0;
            int currentTime = 0;
            String currentTagName = "";

            while (numberOfTimers < maxNumberOfTimers) {
                numberOfTimers = numberOfTimers + 1;
                index0 = entity.getPersistentData().getStringOr("TimerLoops", "").indexOf("" + numberOfTimers + ":[");
                index1 = index0 + entity.getPersistentData().getStringOr("TimerLoops", "").substring(index0).indexOf(":[") + 2;
                index2 = index1 + entity.getPersistentData().getStringOr("TimerLoops", "").substring(index1).indexOf("]");
                currentTagName = entity.getPersistentData().getStringOr("TimerLoops", "").substring(index1, index2);

                if (!NBTTimerLoopDat.getCurrentDoseCountDownOfLoop(entity, currentTagName)) {
                    if (currentTagName.length() >= 1) {
                        if (!NBTTimerLoopDat.getCurrentIsPausedOfLoop(entity, currentTagName)) {
                            currentTime = NBTTimerLoopDat.getCurrentTimeOfLoop(entity, currentTagName);
                            if (currentTime <= 5999) {
                                NBTTimerLoopAct.setTicksOfTimerTag(entity, currentTagName, currentTime + 1);
                            }
                        }
                    }
                } else {
                    if (currentTagName.length() >= 1) {
                        if (!NBTTimerLoopDat.getCurrentIsPausedOfLoop(entity, currentTagName)) {
                            currentTime = NBTTimerLoopDat.getCurrentTimeOfLoop(entity, currentTagName);
                            if (currentTime >= 1) {
                                NBTTimerLoopAct.setTicksOfTimerTag(entity, currentTagName, currentTime - 1);
                            }
                        }
                    }
                }

            }

        }
    }
}
