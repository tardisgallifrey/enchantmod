package com.tardisgallifrey.enchantmod.Init;

import com.tardisgallifrey.enchantmod.EnchantModMain;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;


//This is the Item Init(ialization) class
//There can be one for Blocks and
// ones for other Entities

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ItemInit {

    //no constructor in this class


    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS,
                    EnchantModMain.MOD_ID);


    public static class ModCreativeTab extends CreativeModeTab {
        private ModCreativeTab(int index, String label) {
            super(index, label);
        }

        @Override
        public @NotNull ItemStack makeIcon() {

            return null;
        }

        public static final ModCreativeTab instance =
                new ModCreativeTab(CreativeModeTab.TABS.length,
                        "enchantmod");
    }






}
