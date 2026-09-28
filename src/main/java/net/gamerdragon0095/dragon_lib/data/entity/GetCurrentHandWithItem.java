package net.gamerdragon0095.dragon_lib.data.entity;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class GetCurrentHandWithItem {

    public static ItemStack mainHandPriority(Entity entity, Item item) {
        if (entity == null)
            return ItemStack.EMPTY;
        if (entity instanceof LivingEntity _entity) {

            if (item == ( _entity.getMainHandItem().getItem())) {
                return _entity.getMainHandItem();
            } else if (item == _entity.getOffhandItem().getItem()) {
                  return _entity.getOffhandItem();
                }
            }
        return ItemStack.EMPTY;
    }

    public static ItemStack offHandPriority(Entity entity, Item item) {
        if (entity == null)
            return ItemStack.EMPTY;

        if (entity instanceof LivingEntity _entity) {

            if (item == ( _entity.getOffhandItem().getItem())) {
                return _entity.getOffhandItem();
            } else if (item == _entity.getMainHandItem().getItem()) {
                return _entity.getMainHandItem();
            }
        }
        return ItemStack.EMPTY;
    }

}


