package com.tardisgallifrey.enchantmod.enchants;

import com.tardisgallifrey.enchantmod.Init.EnchantmentInit;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;

public class BridgeEnchantment extends Enchantment {
    public BridgeEnchantment() {
        //These all have to be called from different
        //classes and in some cases, to get around
        //private values in original classes

        //This enchantment targets armor boots (FEET)
        //To target all slots, use EquipmentSlot.values()
        //instead
        super(Enchantment.Rarity.RARE,
                EnchantmentCategory.ARMOR_FEET,
                new EquipmentSlot[]{EquipmentSlot.FEET});
    }


    //Max enchantment level
    @Override
    public int getMaxLevel() {
        return 3;
    }




    //other enchantments that cannot be used
    //at the same time
    @Override
    protected boolean checkCompatibility(@NotNull Enchantment other) {
        return super.checkCompatibility(other) && other !=
                Enchantments.FROST_WALKER;
        //cannot have frost walker and bridge at same time.
    }

    //Doesn't matter if boots are worn,
    //doesn't respect slots
    //OFFENSIVE
    @Override
    public void doPostAttack(@NotNull LivingEntity attacker,
                             Entity target,
                             int level) {
        target.setRemainingFireTicks(40);
    }


    //DEFENSIVE
    @Override
    public void doPostHurt(LivingEntity target, @NotNull Entity attacker,
                           int p_151367_3_) {
        target.addEffect(new MobEffectInstance(
                MobEffects.DAMAGE_BOOST, 100));
    }

    //inner class
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class BridgeBuildingHandler {
        @SubscribeEvent
        public static void buildBridge(TickEvent.PlayerTickEvent event){
            if (event.phase == TickEvent.Phase.END || event.player.level.isClientSide()) return;

            //how to use this class in my own code
            int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.BRIDGE.get(), event.player);

            //Do this if level is 0 and
            //player is holding the SHIFT key down
            if (level > 0 && event.player.isShiftKeyDown()){

                //get Blockstate of block underneath
                //player's feet
                BlockState state = event.player
                        .level
                        .getBlockState(event.player
                                .blockPosition()
                                .below());

                //if the block below isn't
                //air, just return
                if (!state.isAir()) return;
                //otherwise, make block into SLIME
                event.player
                        .level
                        .setBlockAndUpdate(event
                                .player
                                .blockPosition()
                                .below(),
                                Blocks.SLIME_BLOCK
                                        .defaultBlockState());
            }
        }
    }
}
