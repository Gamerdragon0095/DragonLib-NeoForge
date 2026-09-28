package net.gamerdragon0095.dragon_lib.data.entity;

import net.minecraft.world.entity.Entity;

import java.util.Calendar;

public class NBTTimerLoopDat {

    public static int getCurrentTimeOfLoop(Entity entity, String name) {

        if(entity.getPersistentData().getString("" + name).map(str -> str.length() >= 1).orElse(false)) {

            String timerLoopsTag = entity.getPersistentData().getString("TimerLoops").map(str -> str).orElse("");
            String tag = entity.getPersistentData().getString("" + name).map(str -> str).orElse("");

            if (timerLoopsTag.substring(12).contains(name)) {

                double index1 = tag.indexOf("ticks:[", 0) + 7;
                double index2 = tag.indexOf("]", (int) index1);

                int time = new Object() {
                    int convert(String s) {
                        try {
                            return Integer.parseInt(s.trim());
                        } catch (Exception e) {

                        }
                        return 0;
                    }
                }.convert(tag.substring((int) index1, (int) index2));

                return time;
            } else {
                System.out.println("[" + (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + ":" + Calendar.getInstance().get(Calendar.MINUTE) + ":" + Calendar.getInstance().get(Calendar.SECOND)) + "] [DragonLib] Error: NBTTag with the name: '" + name + "' for entity: '" + entity.getStringUUID() + "' is not a timerTag and therefor cannot be read by this action");
                return 0;
            }
        } else {
            System.out.println("[" + (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + ":" + Calendar.getInstance().get(Calendar.MINUTE) + ":" + Calendar.getInstance().get(Calendar.SECOND)) + "] [DragonLib] Error: No such timerTag with the name: '" + name + "' for entity: '" + entity.getStringUUID() + "' exists, please create a tag with this name before trying to read it");
            return 0;
        }
    }

    public static boolean getCurrentIsPausedOfLoop(Entity entity, String name) {

        if(entity.getPersistentData().getString("" + name).map(str -> str.length() >= 1).orElse(false)) {

            String timerLoopsTag = entity.getPersistentData().getString("TimerLoops").map(str -> str).orElse("");
            String tag = entity.getPersistentData().getString("" + name).map(str -> str).orElse("");

            if (timerLoopsTag.substring(12).contains(name)) {

                double index1 = tag.indexOf("isPaused:[", 0) + 10;
                double index2 = tag.indexOf("]", (int) index1);

                boolean isPaused = false;

                if (tag.substring((int) index1, (int) index2).contains("true")) {
                    isPaused = true;
                }

                return isPaused;
            } else {
                System.out.println("[" + (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + ":" + Calendar.getInstance().get(Calendar.MINUTE) + ":" + Calendar.getInstance().get(Calendar.SECOND)) + "] [DragonLib] Error: NBTTag with the name: '" + name + "' for entity: '" + entity.getStringUUID() + "' is not a timerTag and therefor cannot be read by this action");
                return false;
            }
        } else {
            System.out.println("[" + (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + ":" + Calendar.getInstance().get(Calendar.MINUTE) + ":" + Calendar.getInstance().get(Calendar.SECOND)) + "] [DragonLib] Error: No such timerTag with the name: '" + name + "' for entity: '" + entity.getStringUUID() + "' exists, please create a tag with this name before trying to read it");
            return false;
        }
    }

    public static boolean getCurrentDoseCountDownOfLoop(Entity entity, String name) {

        if(entity.getPersistentData().getString("" + name).map(str -> str.length() >= 1).orElse(false)) {

            String timerLoopsTag = entity.getPersistentData().getString("TimerLoops").map(str -> str).orElse("");
            String tag = entity.getPersistentData().getString("" + name).map(str -> str).orElse("");

            if (timerLoopsTag.substring(12).contains(name)) {

                double index1 = tag.indexOf("doseCountDown:[", 0) + 15;
                double index2 = tag.indexOf("]", (int) index1);

                boolean doseCountDown = false;

                if (tag.substring((int) index1, (int) index2).contains("true")) {
                    doseCountDown = true;
                }

                return doseCountDown;
            } else {
                System.out.println("[" + (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + ":" + Calendar.getInstance().get(Calendar.MINUTE) + ":" + Calendar.getInstance().get(Calendar.SECOND)) + "] [DragonLib] Error: NBTTag with the name: '" + name + "' for entity: '" + entity.getStringUUID() + "' is not a timerTag and therefor cannot be read by this action");
                return false;
            }
        } else {
            System.out.println("[" + (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + ":" + Calendar.getInstance().get(Calendar.MINUTE) + ":" + Calendar.getInstance().get(Calendar.SECOND)) + "] [DragonLib] Error: No such timerTag with the name: '" + name + "' for entity: '" + entity.getStringUUID() + "' exists, please create a tag with this name before trying to read it");
            return false;
        }
    }

    public static boolean doesLoopExist(Entity entity, String name) {
        if(entity.getPersistentData().getString("" + name).map(str-> str.length() >= 1).orElse(false)) {

            String timerLoopsTag = entity.getPersistentData().getString("TimerLoops").map(str -> str).orElse("");
            String tag = entity.getPersistentData().getString("" + name).map(str -> str).orElse("");

            if (timerLoopsTag.substring(12).contains(name)) {
                return true;
            } else {
                System.out.println("[" + (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + ":" + Calendar.getInstance().get(Calendar.MINUTE) + ":" + Calendar.getInstance().get(Calendar.SECOND)) + "] [DragonLib] Error: NBTTag with the name: '" + name + "' for entity: '" + entity.getStringUUID() + "' is not a timerTag and therefor cannot be read by this action");
                return false;
            }
        } else {
            System.out.println("[" + (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + ":" + Calendar.getInstance().get(Calendar.MINUTE) + ":" + Calendar.getInstance().get(Calendar.SECOND)) + "] [DragonLib] Error: No such timerTag with the name: '" + name + "' for entity: '" + entity.getStringUUID() + "' exists, please create a tag with this name before trying to read it");
            return false;
        }
    }

}
